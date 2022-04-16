package com.example.boruto_compose.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun writeToDataStore(completed : Boolean)
    fun readFromDataStore() : Flow<Boolean>

}