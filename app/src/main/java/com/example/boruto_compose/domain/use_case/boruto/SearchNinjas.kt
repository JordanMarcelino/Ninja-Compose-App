package com.example.boruto_compose.domain.use_case.boruto

import androidx.paging.PagingData
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.repository.BorutoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchNinjas @Inject constructor(
    private val borutoRepository: BorutoRepository
) {

    suspend operator fun invoke(query: String): Flow<PagingData<Ninja>> {
        return withContext(Dispatchers.IO) {
            borutoRepository.searchNinjas(query = query)
        }
    }

}