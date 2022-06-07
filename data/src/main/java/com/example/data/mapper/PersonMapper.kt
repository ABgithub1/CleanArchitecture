package com.example.data.mapper

import com.example.data.model.PersonDTO
import com.example.data.model.PersonEntity
import com.example.domain.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal fun PersonDTO.toDomainModelPerson(): Person {
    return Person(
        id = id,
        name = name,
        nickname = nickname,
        birthday = birthday,
        status = status,
        img = img
    )
}

internal fun PersonEntity.toDomainModelPerson(): Person {
    return Person(
        id = id,
        name = name,
        nickname = nickname,
        birthday = birthday,
        status = status,
        img = img
    )
}

internal fun Person.toDataModelEntity(): PersonEntity {
    return PersonEntity(
        id = id,
        name = name,
        nickname = nickname,
        birthday = birthday,
        status = status,
        img = img
    )
}

internal fun Person.toDataModelDTO(): PersonDTO {
    return PersonDTO(
        id = id,
        name = name,
        nickname = nickname,
        birthday = birthday,
        status = status,
        img = img
    )
}

internal fun List<PersonDTO>.toDomainModelsPerson(): List<Person> {
    return map { it.toDomainModelPerson() }
}

internal fun List<PersonEntity>.toDomainModelsPerson1(): List<Person> {
    return map { it.toDomainModelPerson() }
}
