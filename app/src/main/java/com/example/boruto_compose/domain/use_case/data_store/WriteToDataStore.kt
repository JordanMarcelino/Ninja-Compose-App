package com.example.boruto_compose.domain.use_case.data_store

import com.example.boruto_compose.domain.repository.DataStoreRepository
import javax.inject.Inject

class WriteToDataStore @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(completed: Boolean) =
        dataStoreRepository.writeToDataStore(completed)

}