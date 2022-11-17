package com.example.domain.core

/**
 * Mapper
 */
interface Mapper<In, Out> {
    /**
     * Map [input] to [Out]
     */
    fun map(input: In): Out
}
