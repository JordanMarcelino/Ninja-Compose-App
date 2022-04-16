package com.example.boruto_compose.ui.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boruto_compose.domain.use_case.data_store.WriteToDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val writeToDataStore: WriteToDataStore,
) : ViewModel() {

    fun setComplete(completed: Boolean) = viewModelScope.launch {
        writeToDataStore(completed)
    }

}