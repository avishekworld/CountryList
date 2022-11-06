package com.example.ui.compose.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val smallestDimen = 1.dp
val mediumDimen = 24.dp
val smallDimen = 8.dp

fun Modifier.padding() = paddingMedium()
fun Modifier.paddingMedium() = padding(all = mediumDimen)
fun Modifier.paddingSmall() = padding(all = smallDimen)

fun Modifier.paddingHorizontalMedium() = padding(horizontal = mediumDimen)
fun Modifier.paddingHorizontalSmall() = padding(horizontal = smallDimen)
fun Modifier.paddingHorizontalSmallest() = padding(horizontal = smallestDimen)
fun Modifier.paddingVerticalMedium() = padding(vertical = mediumDimen)
fun Modifier.paddingVerticalSmall() = padding(vertical = smallDimen)
fun Modifier.paddingVerticalSmallest() = padding(vertical = smallestDimen)

fun Modifier.paddingStartMedium() = padding(start = mediumDimen)
fun Modifier.paddingStartSmall() = padding(start = smallDimen)
fun Modifier.paddingEndMedium() = padding(end = mediumDimen)
fun Modifier.paddingEndSmall() = padding(end = smallDimen)
