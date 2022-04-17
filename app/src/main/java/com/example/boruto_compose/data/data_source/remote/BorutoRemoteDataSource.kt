package com.example.boruto_compose.data.data_source.remote

import androidx.paging.PagingData
import com.example.boruto_compose.domain.model.Ninja
import kotlinx.coroutines.flow.Flow

interface BorutoRemoteDataSource {

    suspend fun getNinjas() : Flow<PagingData<Ninja>>
    suspend fun searchNinjas(query : String) : Flow<PagingData<Ninja>>

}