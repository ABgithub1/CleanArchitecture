package com.example.data.koin

import org.koin.dsl.module

val allDataModule = module {
    includes(
        databaseModule,
        networkModule,
        repositoryModule,
        getPersonsFromApiUseCase,
        deletePersonFromDatabaseUseCase,
        insertPersonToDatabaseUseCase,
        subscribeToChangesDbUseCase
    )
}