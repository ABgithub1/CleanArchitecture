package com.example.data.model

internal data class PersonDTO(
    val id: Long = 0,
    val name: String,
    val nickname: String,
    val birthday: String,
    val status: String,
    var img: String
)