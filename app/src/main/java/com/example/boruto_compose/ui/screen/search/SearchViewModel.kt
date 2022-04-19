package com.example.boruto_compose.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.use_case.boruto.SearchNinjas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNinjas: SearchNinjas
) : ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _ninjas = MutableStateFlow<PagingData<Ninja>>(PagingData.empty())
    val ninjas: StateFlow<PagingData<Ninja>> = _ninjas


    fun onQueryChange(value: String) {
        _query.value = value
    }

    fun searchNinja() = viewModelScope.launch {
        searchNinjas(query.value).cachedIn(viewModelScope).collect {
            _ninjas.value = it
        }
    }

}