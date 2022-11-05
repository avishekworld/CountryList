package com.example.data.country

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Country Data Transfer Object
 * TODO add other properties when needed
 */
@JsonClass(generateAdapter = true)
data class CountryDTO(
    @Json(name = "name") val name: String?,
    @Json(name = "code") val code: String?,
    @Json(name = "region") val region: String?,
    @Json(name = "capital") val capital: String?,
)
