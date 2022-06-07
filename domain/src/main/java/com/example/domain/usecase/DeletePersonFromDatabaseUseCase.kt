package com.example.domain.usecase

import com.example.domain.model.Person
import com.example.domain.repository.PersonLocalRepository

class DeletePersonFromDatabaseUseCase(private val personRepository: PersonLocalRepository) {

    suspend operator fun invoke(person: Person): Result<Unit> {
        return personRepository.deletePersonFromDatabase(person)
    }
}