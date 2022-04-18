package com.example.boruto_compose.data.data_source.remote

import com.example.boruto_compose.domain.model.ApiResponse

interface BorutoRemoteDataSource {

    suspend fun getNinjas(page: Int): ApiResponse
    suspend fun searchNinjas(query: String): ApiResponse

}