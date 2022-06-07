package com.example.data.retrofit

import com.example.data.model.PersonDTO
import retrofit2.http.GET

internal interface BreakingBadApi {
    @GET("characters")
    suspend fun getPersons(): List<PersonDTO>

//    @GET("characters" )
//    suspend fun getPersonByName(name: String): PersonDTO ///

}

// https://www.breakingbadapi.com/api/characters