package com.example.ui.compose.widget

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.compose.R
import com.example.ui.compose.theme.AppBrandTheme
import com.example.ui.compose.theme.paddingStartMedium

@Composable
fun UITopBar(
    title: String,
    canGoBack: Boolean = false,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.onPrimary
    ) {
        if (canGoBack) {
            BackArrowIcon(
                onClick = {
                    onBack()
                }
            )
        }
        Text(
            modifier = Modifier.paddingStartMedium(),
            text = title,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable fun BackArrowIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
    onClick: () -> Unit,
) {
    Image(
        imageVector = imageVector,
        contentDescription = null,
        modifier = modifier
            .clickable {
                onClick()
            }
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UITopBarPreview() {
    AppBrandTheme {
        UITopBar(
            title = "My App",
            canGoBack = true
        )
    }
}
