package com.example.boruto_compose.domain.use_case.boruto

import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.repository.BorutoRepository
import javax.inject.Inject

class GetNinja @Inject constructor(
    private val borutoRepository: BorutoRepository
) {

    suspend operator fun invoke(ninjaId: Int): Ninja {
        return borutoRepository.getNinjaDetails(ninjaId = ninjaId)
    }

}