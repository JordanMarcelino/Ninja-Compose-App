package com.example.boruto_compose.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult
import com.example.RunWithMockitoAndroid
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.domain.model.ApiResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class NinjaRemoteMediatorTestWithMockito : RunWithMockitoAndroid() {

    @Mock
    lateinit var borutoRemoteDataSource: BorutoRemoteDataSource

    @Test
    fun remoteMediator_refreshPage_returnCorrectResult() = runTest {
        `when`(borutoRemoteDataSource.getNinjas(1)).thenReturn(
            ApiResponse(
                success = true,
                prevPage = null,
                nextPage = 2,
                ninjas = emptyList()
            )
        )

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
        verify(borutoRemoteDataSource).getNinjas(1)
    }

    @Test
    fun remoteMediator_endOfPage_returnCorrectResult() = runTest {
        `when`(borutoRemoteDataSource.getNinjas(1)).thenReturn(
            ApiResponse(
                success = true,
                prevPage = null,
                nextPage = null,
                ninjas = emptyList()
            )
        )

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
        verify(borutoRemoteDataSource).getNinjas(1)
    }

    @Test
    fun remoteMediator_invalidPageNumber_returnError() = runTest {
        `when`(borutoRemoteDataSource.getNinjas(1)).then {
            throw IOException()
        }

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
        verify(borutoRemoteDataSource).getNinjas(1)
    }


}