package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val nickname: String,
    val birthday: String,
    val status: String,
    var img: String
)