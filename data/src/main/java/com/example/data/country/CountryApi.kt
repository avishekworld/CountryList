package com.example.data.country

import com.example.domain.core.Result
import com.example.domain.country.Country
import com.example.domain.country.CountryRequest
import com.example.domain.logger.Logger
import retrofit2.http.GET

/**
 * Country Api
 */
interface CountryApi {
    /**
     * Get country list for given [request]
     */
    suspend fun getCountryList(request: CountryRequest): Result<List<Country>>
}

class CountryApiImpl(
    private val retrofitCountryApi: RetrofitCountryApi,
    private val mapper: CountryMapper,
    private val logger: Logger
) : CountryApi {
    override suspend fun getCountryList(request: CountryRequest): Result<List<Country>> {
        return try {
            val countryListDTO = retrofitCountryApi.getCountryList()
            Result.Success(mapper.map(countryListDTO))
        } catch (e: Exception) {
            logger.e(TAG, "getCountryList error", e)
            Result.Failure(e)
        }
    }

    companion object {
        val TAG = CountryApiImpl.javaClass.simpleName
    }
}

/**
 * Retrofit Country Api
 */
interface RetrofitCountryApi {
    /**
     * Get Country List
     */
    @GET("country-list-api")
    suspend fun getCountryList(): List<CountryDTO>
}
