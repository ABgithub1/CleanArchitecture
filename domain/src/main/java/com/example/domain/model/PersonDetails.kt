package com.example.domain.model


data class PersonDetails(
    val id: Long = 0,
    val name: String,
    val nickname: String,
    val birthday: String,
    val status: String,
    var img: String
)
