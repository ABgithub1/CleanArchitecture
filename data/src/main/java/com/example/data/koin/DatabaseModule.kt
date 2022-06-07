package com.example.data.koin

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.PersonDatabase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            PersonDatabase::class.java,
            "personDatabase_db"
        )
            .build()
    }

    single {
        get<PersonDatabase>().personDao()
    }
}