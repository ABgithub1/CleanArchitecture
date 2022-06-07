package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.PersonEntity
import com.example.data.room.PersonDao

@Database(entities = [PersonEntity::class], version = 1)
internal abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

}
