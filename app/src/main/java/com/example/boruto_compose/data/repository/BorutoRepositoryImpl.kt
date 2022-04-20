package com.example.boruto_compose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.data.paging_source.NinjaRemoteMediator
import com.example.boruto_compose.data.paging_source.SearchNinjaPagingSource
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.repository.BorutoRepository
import com.example.boruto_compose.util.Constant.ITEM_PER_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalPagingApi
class BorutoRepositoryImpl @Inject constructor(
    private val borutoRemoteDataSource: BorutoRemoteDataSource,
    private val borutoDatabase: BorutoDatabase
) : BorutoRepository {

    private val ninjaDao = borutoDatabase.getNinjaDao()

    override fun getNinjas(): Flow<PagingData<Ninja>> {
        val pagingSourceFactory = { ninjaDao.getNinjas() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEM_PER_PAGE
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = NinjaRemoteMediator(
                borutoRemoteDataSource = borutoRemoteDataSource,
                borutoDatabase = borutoDatabase
            )
        ).flow
    }

    override fun searchNinjas(query: String): Flow<PagingData<Ninja>> {
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = {
                SearchNinjaPagingSource(
                    borutoRemoteDataSource = borutoRemoteDataSource,
                    query = query
                )
            }
        ).flow
    }

    override suspend fun getNinjaDetails(ninjaId: Int): Ninja {
        return withContext(Dispatchers.IO) {
            ninjaDao.getNinja(ninjaId = ninjaId)
        }
    }
}