package com.example.app.view.core

/**
 * View Effect
 */
sealed class ViewEffect {
    object ShowProcessingView : ViewEffect()
    object HideProcessingView : ViewEffect()
    object Idle : ViewEffect()
}
