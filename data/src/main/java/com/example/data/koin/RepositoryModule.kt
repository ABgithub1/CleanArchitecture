package com.example.data.koin


import com.example.data.repository.LocalRepositoryImpl
import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.repository.PersonLocalRepository
import com.example.domain.repository.PersonRemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {

    singleOf(::LocalRepositoryImpl) {
        bind<PersonLocalRepository>()
    }

    singleOf(::RemoteRepositoryImpl) {
        bind<PersonRemoteRepository>()
    }

}

//    singleOf(::LocalRepositoryImpl) {
//        bind<PersonLocalRepository>()
//        named("Local repository")          // Когда ставлю named() - Вылетает
//    }
//
//    singleOf(::RemoteRepositoryImpl) {
//        bind<PersonRemoteRepository>()
//        named("Remote repository")
//    }
//
//    single<PersonRemoteRepository> {
//        RemoteRepositoryImpl(get())
//    }

//    single<PersonRepository> {
//        PersonRepositoryImpl(get())
//    }

