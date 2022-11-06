package com.example.ui.compose.container

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.compose.theme.AppBrandTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

/**
 * UI Scaffold
 */
@Composable
fun UIScaffold(
    topBar: @Composable (
        scrollState: ScrollState,
        scaffoldState: ScaffoldState
    ) -> Unit,
    content: @Composable (
        paddingValues: PaddingValues,
        scrollState: ScrollState,
        scaffoldState: ScaffoldState
    ) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    AppBrandTheme {
        Scaffold(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsWithImePadding(),
            topBar = {
                topBar(
                    scaffoldState = scaffoldState,
                    scrollState = scrollState
                )
            }
        ) { paddingValues ->
            content(
                paddingValues = paddingValues,
                scaffoldState = scaffoldState,
                scrollState = scrollState
            )
        }
    }
}
