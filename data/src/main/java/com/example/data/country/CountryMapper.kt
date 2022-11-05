package com.example.data.country

import com.example.data.core.Mapper
import com.example.domain.country.Country

/**
 * Map CountryListDTO to List<Country>
 */
class CountryMapper : Mapper<List<CountryDTO>, List<Country>> {

    override fun map(input: List<CountryDTO>): List<Country> {
        return input.map { countryDTO ->
            Country(
                name = countryDTO.name ?: "",
                capital = countryDTO.capital ?: "",
                code = countryDTO.code ?: "",
                region = countryDTO.region ?: "",
            )
        }
    }
}
