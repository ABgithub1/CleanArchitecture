package com.example.domain.usecase

import com.example.domain.model.Person
import com.example.domain.repository.PersonLocalRepository

class InsertPersonToDatabaseUseCase(private val personRepository: PersonLocalRepository) {

    suspend operator fun invoke(person: Person) {
        personRepository.insertPersonToDatabase(person)
    }

}