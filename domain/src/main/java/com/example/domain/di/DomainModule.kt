package com.example.domain.di

import com.example.domain.core.AppCoroutineDispatchers
import com.example.domain.core.CoroutineDispatchers
import com.example.domain.country.GetCountryAsGroupUseCase
import com.example.domain.country.GetCountryListUseCase
import com.example.domain.logger.Logger
import com.example.domain.logger.LoggerImpl
import org.koin.dsl.module

// Domain Dependencies
val domainModule = module {

    factory {
        GetCountryAsGroupUseCase(
            getCountryListUseCase = get(),
            countryGroupMapper = get(),
        )
    }

    factory {
        GetCountryListUseCase(
            countryRepository = get()
        )
    }

    single<CoroutineDispatchers> {
        AppCoroutineDispatchers()
    }

    single<Logger> {
        LoggerImpl()
    }
}
