package com.example.boruto_compose.data.repository

import androidx.paging.PagingData
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.repository.BorutoRepository
import kotlinx.coroutines.flow.Flow

class BorutoRepositoryImpl(
    private val borutoRemoteDataSource: BorutoRemoteDataSource
) : BorutoRepository {

    override suspend fun getNinjas(): Flow<PagingData<Ninja>> {
        return borutoRemoteDataSource.getNinjas()
    }

    override suspend fun searchNinjas(query: String): Flow<PagingData<Ninja>> {
        TODO("Not yet implemented")
    }
}