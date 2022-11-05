package com.example.data.cache

/**
 * Cache
 */
interface Cache<T> {
    /**
     * Save data into cache
     */
    suspend fun save(data: T)

    /**
     * Load data from cache
     */
    suspend fun load(): T?
}
