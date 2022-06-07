package com.example.cleanarchitecture.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.LceState
import com.example.domain.model.Person
import com.example.domain.usecase.GetPersonsFromApiUseCase
import com.example.domain.usecase.InsertPersonToDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFromRetrofitViewModel(
    private val getPersonsFromApiUseCase: GetPersonsFromApiUseCase,
    private val insertPersonToDatabaseUseCase: InsertPersonToDatabaseUseCase
) : ViewModel() {

    private val _lceFlow = MutableStateFlow<LceState<List<Person>>>(LceState.Loading)
    val lceFlow: Flow<LceState<List<Person>>> = _lceFlow.asStateFlow()

    val _searchQueryFlow = MutableStateFlow("")
    val searchQueryFlow = _searchQueryFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getPersonsFromApiUseCase.invoke().onSuccess {
                _lceFlow.tryEmit(LceState.Content(persons = it))
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            searchQueryFlow.debounce { 100 }
                .mapLatest {
                    filterPersonList(it)
                }
        }

    }

    suspend fun filterPersonList(query: String = ""): List<Person> { ///
        return getPersonsFromApiUseCase.invoke().fold(
            onSuccess = { it ->
                it.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            },
            onFailure = {
                emptyList()
            }
        )
    }

    fun addPersonToDb(
        id: Long,
        name: String,
        nickname: String,
        birthday: String,
        status: String,
        img: String
    ) = viewModelScope.launch {
        val person = Person(id, name, nickname, birthday, status, img)
        insertPersonToDatabaseUseCase.invoke(person)
    }

}