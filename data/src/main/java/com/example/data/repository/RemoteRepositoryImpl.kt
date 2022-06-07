package com.example.data.repository

import com.example.data.mapper.toDomainModelsPerson
import com.example.data.retrofit.BreakingBadApi
import com.example.domain.model.Person
import com.example.domain.repository.PersonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RemoteRepositoryImpl(private val breakingBadApi: BreakingBadApi) :
    PersonRemoteRepository {

    override suspend fun getPersonListFromApi(): Result<List<Person>> {
        return withContext(Dispatchers.IO) {
            runCatching { breakingBadApi.getPersons().toDomainModelsPerson() }
        }
    }

}