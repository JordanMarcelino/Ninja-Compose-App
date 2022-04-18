package com.example.boruto_compose.data.data_source.remote

import androidx.paging.ExperimentalPagingApi
import com.example.boruto_compose.domain.model.ApiResponse
import javax.inject.Inject

@ExperimentalPagingApi
class BorutoRemoteDataSourceImpl @Inject constructor(
    private val borutoApi: BorutoApi
) : BorutoRemoteDataSource {

    override suspend fun getNinjas(page: Int): ApiResponse {
        return borutoApi.getNinjas(page = page)
    }

    override suspend fun searchNinjas(query: String): ApiResponse {
        return borutoApi.searchNinjas(query = query)
    }
}