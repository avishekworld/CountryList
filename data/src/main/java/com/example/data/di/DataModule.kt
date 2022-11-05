package com.example.data.di

import com.example.data.cache.Cache
import com.example.data.country.CountryApi
import com.example.data.country.CountryApiImpl
import com.example.data.country.CountryInMemoryCache
import com.example.data.country.CountryMapper
import com.example.data.country.CountryRepositoryImpl
import com.example.data.country.RetrofitCountryApi
import com.example.domain.country.Country
import com.example.domain.country.CountryRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

const val COUNTRY_API_BASE_URL = "https://tinyurl.com/"

// Data dependencies
val dataModule = module {

    single<CountryRepository> {
        CountryRepositoryImpl(
            countryApi = get(),
            cache = get(),
            logger = get()
        )
    }

    single<CountryApi> {
        CountryApiImpl(
            retrofitCountryApi = get(),
            mapper = get(),
            logger = get()
        )
    }

    single<RetrofitCountryApi> {
        get<Retrofit>().create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(COUNTRY_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<Cache<List<Country>>> {
        CountryInMemoryCache()
    }

    single {
        CountryMapper()
    }
}
