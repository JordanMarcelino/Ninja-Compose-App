package com.example.boruto_compose.domain.use_case.boruto

import androidx.paging.PagingData
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.repository.BorutoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNinjas @Inject constructor(
    private val borutoRepository: BorutoRepository
) {

    operator fun invoke(): Flow<PagingData<Ninja>> {
        return borutoRepository.getNinjas()
    }

}