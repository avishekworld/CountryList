package com.example.data.country

import com.example.data.cache.Cache
import com.example.domain.core.Result
import com.example.domain.country.Country
import com.example.domain.country.CountryRepository
import com.example.domain.country.CountryRequest
import com.example.domain.logger.Logger

/**
 * Country Repository Impl
 */
class CountryRepositoryImpl(
    private val countryApi: CountryApi,
    private val cache: Cache<List<Country>>,
    private val logger: Logger
) : CountryRepository {
    override suspend fun getCountryList(request: CountryRequest): Result<List<Country>> {
        return try {
            if (request.forceRefresh) {
                when (val result = countryApi.getCountryList(request)) {
                    is Result.Success -> {
                        cache.save(result.data)
                        result
                    }
                    is Result.Failure -> result
                }
            } else {
                cache.load()?.let { Result.Success(it) } ?: Result.Failure(Exception("Country List Cache is empty"))
            }
        } catch (e: Exception) {
            logger.e(TAG, "getCountryList error", e)
            Result.Failure(e)
        }
    }
    companion object {
        val TAG = CountryRepositoryImpl.javaClass.simpleName
    }
}

/**
 * Country In Memory Cache
 * TODO: add local storage cache with DB
 */
class CountryInMemoryCache : Cache<List<Country>> {
    private var countryList: List<Country> = emptyList()

    override suspend fun save(data: List<Country>) {
        countryList = data
    }

    override suspend fun load(): List<Country>? {
        return countryList
    }
}
