package com.example.data.repository


import com.example.data.mapper.toDataModelEntity
import com.example.data.mapper.toDomainModelPerson
import com.example.data.mapper.toDomainModelsPerson1
import com.example.data.room.PersonDao
import com.example.domain.model.Person
import com.example.domain.repository.PersonLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class LocalRepositoryImpl(private val dao: PersonDao) : PersonLocalRepository {

    override suspend fun getAllPersonsFromDatabase(): Result<List<Person>> {
        return withContext(Dispatchers.IO) {
            runCatching { dao.getAll().toDomainModelsPerson1() }
        }
    }

    override suspend fun insertPersonToDatabase(person: Person): Result<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching { dao.insertPerson(person.toDataModelEntity()) }
        }
    }

    override suspend fun loadPersonByIdFromDatabase(id: Long): Result<Person> {
        return withContext(Dispatchers.IO) {
            runCatching { dao.loadPersonById(id).toDomainModelPerson() }
        }
    }

    override suspend fun deletePersonFromDatabase(person: Person): Result<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching { dao.delete(person.toDataModelEntity()) }
        }
    }

    override suspend fun subscribeChangesDb(): Result<Flow<List<Person>>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                dao.subscribeChanges().map {
                    it.toDomainModelsPerson1()
                }
            }
        }
    }

}