package com.example.boruto_compose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.use_case.boruto.GetNinjas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getNinjas: GetNinjas
) : ViewModel() {

    private val _ninjas = mutableStateOf(getNinjas())
    val ninjas: State<Flow<PagingData<Ninja>>> = _ninjas

}