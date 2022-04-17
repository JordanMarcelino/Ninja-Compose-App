package com.example.boruto_compose.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.data.data_source.remote.BorutoApi
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.model.NinjaRemoteKeys

@ExperimentalPagingApi
class NinjaRemoteMediator(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int, Ninja>() {

    private val ninjaDao = borutoDatabase.getNinjaDao()
    private val ninjaRemoteKeysDao = borutoDatabase.getNinjaRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Ninja>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val keys = getRemoteKeyClosestToCurrentPosition(state)
                    keys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val keys = getRemoteKeyForFirstItem(state)
                    val page = keys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = keys != null
                    )
                    page
                }
                LoadType.APPEND -> {
                    val keys = getRemoteKeyForLastItem(state)
                    val page = keys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = keys != null
                    )
                    page
                }
            }

            val response = borutoApi.getNinjas(page = page)

            if (response.ninjas.isNotEmpty()) {
                borutoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        ninjaDao.deleteAllNinjas()
                        ninjaRemoteKeysDao.deleteAllNinjaRemoteKeys()
                    }
                    val keys = response.ninjas.map { ninja ->
                        ninja.toNinjaRemoteKey(
                            id = ninja.id,
                            prevPage = response.prevPage,
                            nextPage = response.nextPage
                        )
                    }

                    ninjaRemoteKeysDao.addNinjaRemoteKeys(keys)
                    ninjaDao.addNinjas(response.ninjas)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = response.nextPage == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Ninja>
    ): NinjaRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                ninjaRemoteKeysDao.getNinjaRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Ninja>
    ): NinjaRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { ninja ->
            ninjaRemoteKeysDao.getNinjaRemoteKeys(ninja.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Ninja>
    ): NinjaRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { ninja ->
            ninjaRemoteKeysDao.getNinjaRemoteKeys(ninja.id)
        }
    }
}