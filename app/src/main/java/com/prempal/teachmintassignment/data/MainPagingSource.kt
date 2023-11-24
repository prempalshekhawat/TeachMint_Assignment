package com.prempal.teachmintassignment.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prempal.teachmintassignment.data.remote.MainService
import com.prempal.teachmintassignment.data.remote.response.MainResponse
import retrofit2.HttpException
import java.io.IOException

class MainPagingSource(
    private val mainService: MainService,
    private val query: String
) : PagingSource<Int, MainResponse.Item>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MainResponse.Item> {
        return try {
            val position = params.key ?: 0
            val response = mainService.searchRepos(
                page = position,
                perPage = params.loadSize,
                query = query
            )

            LoadResult.Page(
                data = response.items ?: emptyList(),
                if (position == 0) null else 0,
                if (response.items!!.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MainResponse.Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}