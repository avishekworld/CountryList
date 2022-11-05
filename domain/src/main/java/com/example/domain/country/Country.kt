package com.example.domain.country

import com.example.domain.core.Result
import com.example.domain.core.SuspendUseCase

/**
 * Get Country List UseCase
 */
class GetCountryListUseCase(private val countryRepository: CountryRepository) :
    SuspendUseCase<CountryRequest, Result<List<Country>>> {
    override suspend fun run(request: CountryRequest): Result<List<Country>> {
        return countryRepository.getCountryList(request)
    }
}

/**
 * Country Repository
 */
interface CountryRepository {
    /**
     * Get country list for given [request]
     */
    suspend fun getCountryList(request: CountryRequest): Result<List<Country>>
}

/**
 * Country
 */
data class Country(
    val name: String = "",
    val region: String = "",
    val code: String = "",
    val capital: String = ""
) {
    /**
     * Return true if name, region, code and capital is not blank
     */
    fun isValid(): Boolean {
        return name.isNotBlank() &&
            region.isNotBlank() &&
            code.isNotBlank() &&
            capital.isNotBlank()
    }
}

/**
 * Country Request
 * TODO add other properties
 */
data class CountryRequest(val forceRefresh: Boolean = false)
