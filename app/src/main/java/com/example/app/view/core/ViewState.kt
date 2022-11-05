package com.example.app.view.core

/**
 * View State
 */
sealed class ViewState {
    object Show : ViewState()
    object Disabled : ViewState()
    object Hide : ViewState()
}
