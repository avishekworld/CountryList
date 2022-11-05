package com.example.data.core

/**
 * Mapper
 */
interface Mapper<In, Out> {
    /**
     * Map [input] to [Out]
     */
    fun map(input: In): Out
}
