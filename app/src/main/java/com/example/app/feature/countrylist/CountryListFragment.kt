package com.example.app.feature.countrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.ui.compose.container.UIScaffold
import com.example.ui.compose.widget.UITopBar
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
                UIScaffold(topBar = { _, _ ->
                    UITopBar(
                        title = stringResource(id = R.string.app_name),
                    )
                }) { _, _, _ ->
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
