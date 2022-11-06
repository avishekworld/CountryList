package com.example.app.feature.countrylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.view.core.ViewState
import com.example.domain.country.Country
import com.example.ui.compose.theme.paddingHorizontalMedium
import com.example.ui.compose.theme.paddingVerticalSmall
import com.example.ui.compose.theme.paddingVerticalSmallest
import com.example.ui.compose.widget.ProcessingView
import com.example.ui.compose.widget.UISpacer

/**
 * Country List Screen
 */
@Composable
fun CountryListScreen(
    viewState: CountryListViewState,
    onRetryClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (viewState.errorViewState is ViewState.Show) {
            Error(onRetryClicked = onRetryClicked)
        } else {
            CountryList(countryList = viewState.countryList)
        }
    }

    ProcessingView(show = viewState.processingViewState is ViewState.Show)
}

@Composable
private fun CountryList(countryList: List<Country>) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(countryList) { item ->
                CountryItem(country = item)
                UISpacer(modifier = Modifier.paddingVerticalSmallest())
            }
        }
    }
}

@Composable
private fun CountryItem(country: Country) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .paddingHorizontalMedium()
                .paddingVerticalSmall()
        ) {
            Row {
                Text(text = "${country.name}, ")
                Text(text = "${country.region}")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text = "${country.code}")
                }
            }
            UISpacer(modifier = Modifier.paddingVerticalSmall())
            Row {
                Text(text = "${country.capital}")
            }
        }
    }
}

@Composable
private fun Error(onRetryClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paddingVerticalSmall(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.error_loading_data),
            color = MaterialTheme.colors.error
        )
        Button(onClick = onRetryClicked) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}
