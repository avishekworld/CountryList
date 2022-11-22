package com.example.domain.core

import com.example.domain.country.Country
import com.example.domain.country.CountryGroup
import com.example.domain.country.CountryGroupList

class CountryGroupMapper : Mapper<List<Country>, CountryGroupList> {

    // TODO add test
    override fun map(countryList: List<Country>): CountryGroupList {
        val countryGroupMap = countryList.groupBy { "${it.name.firstOrNull() ?: ""}" }
        val countryGroupList = countryGroupMap.map { mapEntry ->
            CountryGroup(
                groupName = mapEntry.key,
                countryList = mapEntry.value
            )
        }
        return CountryGroupList(
            groupList = countryGroupList
        )
    }
}
