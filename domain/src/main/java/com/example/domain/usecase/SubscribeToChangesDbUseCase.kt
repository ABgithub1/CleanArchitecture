package com.example.domain.usecase

import com.example.domain.model.Person
import com.example.domain.repository.PersonLocalRepository
import kotlinx.coroutines.flow.Flow

class SubscribeToChangesDbUseCase(private val personRepository: PersonLocalRepository) {
    suspend operator fun invoke(): Result<Flow<List<Person>>> {
        return personRepository.subscribeChangesDb()
    }
}