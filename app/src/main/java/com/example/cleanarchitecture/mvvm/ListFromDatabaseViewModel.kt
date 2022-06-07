package com.example.cleanarchitecture.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.LceState
import com.example.domain.model.Person
import com.example.domain.usecase.DeletePersonFromDatabaseUseCase
import com.example.domain.usecase.SubscribeToChangesDbUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListFromDatabaseViewModel(
    private val subscribeToChangesDbUseCase: SubscribeToChangesDbUseCase,
    private val deletePersonFromDatabaseUseCase: DeletePersonFromDatabaseUseCase
) : ViewModel() {

    private val _lceDatabaseFlow = MutableStateFlow<LceState<Flow<List<Person>>>>(LceState.Loading)
    val lceDatabaseFlow: Flow<LceState<Flow<List<Person>>>> = _lceDatabaseFlow.asStateFlow()

    init {
        viewModelScope.launch {
            subscribeToChangesDbUseCase.invoke().fold(
                onSuccess = {
                    _lceDatabaseFlow.tryEmit(LceState.Content(persons = it))
                },
                onFailure = { exception ->
                    exception
                }
            )
        }
    }

    fun onPersonSwiped(person: Person) = viewModelScope.launch {
        deletePersonFromDatabaseUseCase.invoke(person)
    }

}