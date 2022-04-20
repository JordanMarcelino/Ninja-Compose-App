package com.example.boruto_compose.domain.repository

import androidx.paging.PagingData
import com.example.boruto_compose.domain.model.Ninja
import kotlinx.coroutines.flow.Flow

interface BorutoRepository {

    fun getNinjas() : Flow<PagingData<Ninja>>
    fun searchNinjas(query : String) : Flow<PagingData<Ninja>>
    suspend fun getNinjaDetails(ninjaId : Int) : Ninja
}