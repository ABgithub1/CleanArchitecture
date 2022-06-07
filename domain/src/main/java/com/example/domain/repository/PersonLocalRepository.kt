package com.example.domain.repository

import com.example.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonLocalRepository {

    suspend fun getAllPersonsFromDatabase(): Result<List<Person>>

    suspend fun insertPersonToDatabase(person: Person): Result<Unit>

    suspend fun loadPersonByIdFromDatabase(id: Long): Result<Person>

    suspend fun deletePersonFromDatabase(person: Person): Result<Unit>

    suspend fun subscribeChangesDb(): Result<Flow<List<Person>>>

}