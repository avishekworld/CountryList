package com.example.domain.core

/**
 * Result type of Success and Failure
 */
sealed class Result<T> {
    data class Success<T>(val t: T) : Result<T>()
    data class Failure<T>(val t: Throwable) : Result<T>()
}
