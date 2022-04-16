package com.example.boruto_compose.domain.use_case.data_store

import com.example.boruto_compose.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadFromDataStore @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke() : Flow<Boolean> = dataStoreRepository.readFromDataStore()

}