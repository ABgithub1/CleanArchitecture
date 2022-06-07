package com.example.domain.repository

import com.example.domain.model.Person

interface PersonRemoteRepository {

    suspend fun getPersonListFromApi(): Result<List<Person>>

}