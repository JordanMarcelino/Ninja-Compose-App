package com.example.boruto_compose.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.boruto_compose.data.data_source.local.db.BorutoDatabase
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.data.data_source.remote.FakeBorutoRemoteDataSourceAt
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class NinjaRemoteMediatorTest {

    lateinit var borutoRemoteDataSource: BorutoRemoteDataSource
    lateinit var borutoDatabase: BorutoDatabase

    @Before
    fun setUp() {
        borutoRemoteDataSource = FakeBorutoRemoteDataSourceAt()
        borutoDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BorutoDatabase::class.java
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        borutoDatabase.close()
    }

    @Test
    fun remoteMediator_refreshPage_returnCorrectResult() = runTest {
        val source = NinjaRemoteMediator(
            borutoRemoteDataSource = borutoRemoteDataSource,
            borutoDatabase = borutoDatabase
        )
        val actual = source.load(
            loadType = LoadType.REFRESH,
            state = PagingState(
                pages = listOf(),
                config = PagingConfig(pageSize = 3),
                anchorPosition = null,
                leadingPlaceholderCount = 0
            )
        )

        assertThat(actual is MediatorResult.Success).isTrue()
        assertThat((actual as MediatorResult.Success).endOfPaginationReached).isEqualTo(false)
    }

    @Test
    fun remoteMediator_endOfPage_returnCorrectResult() = runTest {
        (borutoRemoteDataSource as FakeBorutoRemoteDataSourceAt).setPage1Empty()
        val source = NinjaRemoteMediator(
            borutoRemoteDataSource = borutoRemoteDataSource,
            borutoDatabase = borutoDatabase
        )
        val actual = source.load(
            loadType = LoadType.REFRESH,
            state = PagingState(
                pages = listOf(),
                config = PagingConfig(pageSize = 3),
                anchorPosition = null,
                leadingPlaceholderCount = 0
            )
        )

        assertThat(actual is MediatorResult.Success).isTrue()
        assertThat((actual as MediatorResult.Success).endOfPaginationReached).isEqualTo(true)
    }

    @Test
    fun remoteMediator_invalidPageNumber_returnError() = runTest {
        (borutoRemoteDataSource as FakeBorutoRemoteDataSourceAt).returnException()
        val source = NinjaRemoteMediator(
            borutoRemoteDataSource = borutoRemoteDataSource,
            borutoDatabase = borutoDatabase
        )
        val actual = source.load(
            loadType = LoadType.REFRESH,
            state = PagingState(
                pages = listOf(),
                config = PagingConfig(pageSize = 3),
                anchorPosition = null,
                leadingPlaceholderCount = 0
            )
        )

        assertThat(actual is MediatorResult.Error).isTrue()
    }


}