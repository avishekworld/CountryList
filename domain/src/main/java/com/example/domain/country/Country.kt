package com.example.domain.country

import com.example.domain.core.CountryGroupMapper
import com.example.domain.core.Result
import com.example.domain.core.SuspendUseCase

class GetCountryAsGroupUseCase(
    private val getCountryListUseCase: GetCountryListUseCase,
    private val countryGroupMapper: CountryGroupMapper
) :
    SuspendUseCase<CountryRequest, Result<CountryGroupList>> {
    override suspend fun run(request: CountryRequest): Result<CountryGroupList> {
        return when (val countryListResult = getCountryListUseCase.run(request)) {
            is Result.Success -> Result.Success(
                data = sortByGroupName(
                    countryGroupList = countryGroupMapper.map(countryListResult.data)
                )
            )
            is Result.Failure -> Result.Failure(countryListResult.error)
        }
    }

    // TODO put into a util or usecase
    private fun sortByGroupName(countryGroupList: CountryGroupList): CountryGroupList {
        return countryGroupList.copy(
            groupList = countryGroupList.groupList.sortedBy {
                it.groupName
            }
        )
    }
}
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

data class CountryGroupList(
    val groupList: List<CountryGroup> = emptyList()
)

data class CountryGroup(
    val groupName: String = "",
    val countryList: List<Country> = emptyList()
)

/**
 * Country Request
 * TODO add other properties
 */
data class CountryRequest(val forceRefresh: Boolean = false)
