package com.example.boruto_compose.data.data_source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.data.paging_source.NinjaRemoteMediator
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.util.Constant.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class BorutoRemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : BorutoRemoteDataSource {

    private val ninjaDao = borutoDatabase.getNinjaDao()

    override suspend fun getNinjas(): Flow<PagingData<Ninja>> {
        val pagingSourceFactory = { ninjaDao.getNinjas() }

        return Pager(
            config = PagingConfig(
                pageSize = ITEM_PER_PAGE
            ),
            remoteMediator = NinjaRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun searchNinjas(query: String): Flow<PagingData<Ninja>> {
        TODO("Not yet implemented")
    }
}