package com.example.data.koin

import com.example.domain.usecase.DeletePersonFromDatabaseUseCase
import com.example.domain.usecase.GetPersonsFromApiUseCase
import com.example.domain.usecase.InsertPersonToDatabaseUseCase
import com.example.domain.usecase.SubscribeToChangesDbUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val getPersonsFromApiUseCase = module {
    factoryOf(::GetPersonsFromApiUseCase)
}

val deletePersonFromDatabaseUseCase = module {
    factoryOf(::DeletePersonFromDatabaseUseCase)
}

val insertPersonToDatabaseUseCase = module {
    factoryOf(::InsertPersonToDatabaseUseCase)
}

val subscribeToChangesDbUseCase = module {
    factoryOf(::SubscribeToChangesDbUseCase)
}

