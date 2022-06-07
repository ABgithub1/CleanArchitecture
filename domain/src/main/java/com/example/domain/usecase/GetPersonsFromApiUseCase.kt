package com.example.domain.usecase

import com.example.domain.model.Person
import com.example.domain.repository.PersonRemoteRepository

class GetPersonsFromApiUseCase(private val personRepository: PersonRemoteRepository) {

    suspend operator fun invoke(): Result<List<Person>>{
        return personRepository.getPersonListFromApi()
    }

//    return withContext(Dispatchers.IO){
//        personRepository.getAllPersonsFromDatabase()
//    }

}