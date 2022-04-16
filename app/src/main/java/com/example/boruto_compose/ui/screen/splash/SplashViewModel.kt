package com.example.boruto_compose.ui.screen.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boruto_compose.domain.use_case.data_store.ReadFromDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readFromDataStore: ReadFromDataStore
) : ViewModel() {

    private val _shouldNavigate = mutableStateOf(false)
    val shouldNavigate: State<Boolean> = _shouldNavigate

    init {
        viewModelScope.launch {
            _shouldNavigate.value = readFromDataStore().stateIn(viewModelScope).value
        }
    }
}