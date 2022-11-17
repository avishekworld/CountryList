package com.example.app.feature.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.view.core.ViewState
import com.example.domain.core.Result
import com.example.domain.country.Country
import com.example.domain.country.CountryGroupList
import com.example.domain.country.CountryRequest
import com.example.domain.country.GetCountryListUseCase
import com.example.domain.logger.Logger
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Country List ViewModel
 */
class CountryListViewModel(
    private val getCountryListUseCase: GetCountryListUseCase,
    private val logger: Logger
) : ViewModel() {

    private val internalViewState = MutableStateFlow<CountryListViewState>(CountryListViewState())
    val viewState: StateFlow<CountryListViewState>
        get() = internalViewState

    private val channel = Channel<CountryListViewEvent>(Channel.UNLIMITED)

    init {
        observerViewEvent()
    }

    private fun observerViewEvent() {
        channel
            .consumeAsFlow()
            .onEach { event ->
                when (event) {
                    is CountryListViewEvent.Init -> handleInit(event)
                    is CountryListViewEvent.RetryClicked -> handleRetryClicked(event)
                }
            }.catch { error ->
                logger.e(TAG, "observerViewEvent error", error)
            }
            .launchIn(viewModelScope)
    }

    private fun updateViewState(viewState: CountryListViewState) {
        internalViewState.value = viewState
    }

    fun handleEvent(event: CountryListViewEvent) {
        channel.trySend(event)
    }

    private fun handleInit(event: CountryListViewEvent.Init) {
        // Get country list only when view state country list is empty
        if (viewState.value.countryList.isEmpty()) {
            getCountryList()
        }
    }

    private fun handleRetryClicked(event: CountryListViewEvent.RetryClicked) {
        getCountryList()
    }

    /**
     * Get Country List
     */
    fun getCountryList() {
        viewModelScope.launch {
            updateViewState(
                viewState.value.copy(
                    processingViewState = ViewState.Show
                )
            )
            val countryListResult = getCountryListUseCase.run(
                request = CountryRequest(forceRefresh = true)
            )
            when (countryListResult) {
                is Result.Success -> updateViewState(
                    viewState.value.copy(
                        countryList = countryListResult.data,
                        errorViewState = if (countryListResult.data.isEmpty()) ViewState.Show else ViewState.Hide,
                        processingViewState = ViewState.Hide
                    )
                )
                is Result.Failure -> updateViewState(
                    viewState.value.copy(
                        errorViewState = ViewState.Show,
                        processingViewState = ViewState.Hide
                    )
                )
            }
        }
    }

    companion object {
        val TAG = CountryListViewModel.javaClass.simpleName
    }
}

/**
 * Country List View Event
 */
sealed class CountryListViewEvent {
    object Init : CountryListViewEvent()
    object RetryClicked : CountryListViewEvent()
}

/**
 * Country List View State
 */
data class CountryListViewState(
    val countryList: List<Country> = emptyList(),
    val countryGroupList: CountryGroupList = CountryGroupList(),
    val errorViewState: ViewState = ViewState.Hide,
    val processingViewState: ViewState = ViewState.Hide
)
