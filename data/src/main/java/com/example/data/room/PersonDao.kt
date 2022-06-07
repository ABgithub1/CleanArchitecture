package com.example.data.room

import androidx.room.*
import com.example.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PersonDao {

    @Query("SELECT * FROM personentity")
    suspend fun getAll(): List<PersonEntity>

    @Query("SELECT * FROM PersonEntity WHERE id LIKE (:id)")
    suspend fun loadPersonById(id: Long): PersonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Query("SELECT * FROM personentity")
    fun subscribeChanges(): Flow<List<PersonEntity>>

    @Delete
    suspend fun delete(person: PersonEntity)

}