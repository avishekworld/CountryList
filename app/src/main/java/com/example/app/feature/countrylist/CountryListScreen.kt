package com.example.app.feature.countrylist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.view.core.ViewState
import com.example.domain.country.Country
import com.example.domain.country.CountryGroupList
import com.example.ui.compose.theme.AppBrandTheme
import com.example.ui.compose.theme.paddingHorizontalMedium
import com.example.ui.compose.theme.paddingStartMedium
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
            CountryGroupList(countryGroupList = viewState.countryGroupList)
        }
    }

    ProcessingView(show = viewState.processingViewState is ViewState.Show)
}

@Composable
private fun CountryGroupList(countryGroupList: CountryGroupList) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            countryGroupList.groupList.forEach { countryGroup ->

                item {
                    CountryGroupHeader(countryGroup.groupName)
                }

                items(countryGroup.countryList) { country ->
                    CountryItem(country = country)
                    UISpacer(modifier = Modifier.paddingVerticalSmallest())
                }
            }
        }
    }
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
private fun CountryGroupHeader(header: String) {
    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = 50.dp)
    ) {
        Text(
            modifier = Modifier.paddingStartMedium(),
            style = MaterialTheme.typography.h4,
            text = header
        )
    }
}

@Composable
// TODO check how to insert and identify an item like recylcer view
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

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UITopBarPreview() {
    AppBrandTheme {
        CountryListScreen(
            viewState = CountryListViewState(
                countryList = listOf(
                    Country(
                        name = "USA",
                        region = "NA",
                        code = "US",
                        capital = "Washington DC"
                    )
                )
            ),
            onRetryClicked = {}
        )
    }
}
