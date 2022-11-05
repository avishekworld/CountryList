package com.example.app.feature.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.view.core.ViewState
import com.example.domain.country.Country
import com.example.domain.country.CountryRequest
import com.example.domain.country.GetCountryListUseCase
import com.example.domain.logger.Logger
import kotlinx.coroutines.launch

/**
 * Country List ViewModel
 */
class CountryListViewModel(
    private val getCountryListUseCase: GetCountryListUseCase,
    private val logger: Logger
) : ViewModel() {

    /**
     * Get Country List
     */
    fun getCountryList() {
        viewModelScope.launch {
            val countryListResult = getCountryListUseCase.run(
                request = CountryRequest(forceRefresh = true)
            )
        }
    }

    companion object {
        val TAG = CountryListViewModel.javaClass.simpleName
    }
}

/**
 * Country List Event
 */
sealed class CountryListEvent {
    object Init : CountryListEvent()
    object RetryClicked : CountryListEvent()
}

/**
 * Country List View State
 */
data class CountryListViewState(
    val countryList: List<Country> = emptyList(),
    val processingViewState: ViewState = ViewState.Hide
)
