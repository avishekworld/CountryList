package com.example.app.di

import com.example.app.feature.countrylist.CountryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// App dependencies
val appModule = module {

    viewModel {
        CountryListViewModel(
            getCountryListUseCase = get(),
            logger = get()
        )
    }
}
