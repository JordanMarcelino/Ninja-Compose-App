package com.example.boruto_compose.ui.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.use_case.boruto.GetNinja
import com.example.boruto_compose.util.Constant.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getNinja: GetNinja,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _ninja = mutableStateOf<Ninja?>(null)
    val ninja: State<Ninja?> = _ninja

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _color = mutableStateOf<Map<String, String>>(mapOf())
    val color: State<Map<String, String>> = _color

    init {
        viewModelScope.launch {
            val ninjaId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            ninjaId?.let { id ->
                _ninja.value = getNinja(ninjaId = id)
            }
        }
    }

    fun onEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }

    fun setColorPalette(
        color: Map<String, String>
    ) {
        viewModelScope.launch {
            _color.value = color
        }
    }
}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}