package com.example.app.feature.countrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.ui.compose.theme.AppBrandTheme
import org.koin.android.ext.android.inject

class CountryListFragment : Fragment() {

    private val viewModel: CountryListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val viewState = viewModel.viewState.collectAsState()
                AppBrandTheme {
                    CountryListScreen(
                        viewState = viewState.value,
                        onRetryClicked = {
                            viewModel.handleEvent(CountryListViewEvent.RetryClicked)
                        }
                    )
                }
            }
            viewModel.getCountryList()
        }
    }
}
