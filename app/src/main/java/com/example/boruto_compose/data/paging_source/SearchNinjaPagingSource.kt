package com.example.boruto_compose.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.boruto_compose.data.data_source.remote.BorutoRemoteDataSource
import com.example.boruto_compose.domain.model.Ninja
import okio.IOException
import retrofit2.HttpException

class SearchNinjaPagingSource(
    private val borutoRemoteDataSource: BorutoRemoteDataSource,
    private val query: String
) : PagingSource<Int, Ninja>() {

    override fun getRefreshKey(state: PagingState<Int, Ninja>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Ninja> {
        return try {
            val response = borutoRemoteDataSource.searchNinjas(query = query)

            if (response.ninjas.isNotEmpty()) {
                LoadResult.Page(
                    data = response.ninjas,
                    prevKey = response.prevPage,
                    nextKey = response.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = response.prevPage,
                    nextKey = response.nextPage
                )
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}