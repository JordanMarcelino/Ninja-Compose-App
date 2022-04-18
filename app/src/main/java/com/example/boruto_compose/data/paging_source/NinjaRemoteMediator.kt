package com.example.boruto_compose.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.domain.model.Ninja
import com.example.boruto_compose.domain.model.NinjaRemoteKeys
import okio.IOException
import retrofit2.HttpException

@ExperimentalPagingApi
class NinjaRemoteMediator(
    private val borutoRemoteDataSource: BorutoRemoteDataSource,
    private val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int, Ninja>() {

    private val ninjaDao = borutoDatabase.getNinjaDao()
    private val ninjaRemoteKeysDao = borutoDatabase.getNinjaRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = ninjaRemoteKeysDao.getNinjaRemoteKeys(1)?.lastUpdated ?: 0L
        val cacheTimeout = 15

        val diff = (currentTime - lastUpdated) / 1000 / 60

        return if (diff <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Ninja>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val keys = getRemoteKeyClosestToCurrentPosition(state)
                    keys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = borutoRemoteDataSource.getNinjas(page = page)

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
                            nextPage = response.nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }

                    ninjaRemoteKeysDao.addNinjaRemoteKeys(keys)
                    ninjaDao.addNinjas(response.ninjas)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = response.nextPage == null
            )
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
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
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data
            ?.firstOrNull()
            ?.let { ninja ->
                ninjaRemoteKeysDao.getNinjaRemoteKeys(ninja.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Ninja>
    ): NinjaRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { ninja ->
                ninjaRemoteKeysDao.getNinjaRemoteKeys(ninja.id)
            }
    }
}