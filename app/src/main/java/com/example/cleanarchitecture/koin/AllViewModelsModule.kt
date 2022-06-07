package com.example.cleanarchitecture.koin

import com.example.cleanarchitecture.mvvm.DetailsViewModel
import com.example.cleanarchitecture.mvvm.ListFromDatabaseViewModel
import com.example.cleanarchitecture.mvvm.ListFromRetrofitViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val allViewModelsModule = module {
    viewModelOf(::ListFromDatabaseViewModel)
    viewModelOf(::ListFromRetrofitViewModel)
    viewModelOf(::DetailsViewModel)
}

