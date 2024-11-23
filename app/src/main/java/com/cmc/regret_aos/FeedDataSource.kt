package com.cmc.regret_aos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.regret_aos.api.ApiService
import java.util.Date

class FeedDataSource(
    private val service: ApiService
) : PagingSource<Int, FeedData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedData> {
        return try {
            val currentPage = params.key ?: 1
            val response = service.getFeedDataList(currentPage, params.loadSize)

            if (response.isSuccessful) {
                val pagedResponse = response.body()!!
                LoadResult.Page(
                    data = pagedResponse.data,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (currentPage == pagedResponse.totalPage) null else currentPage + 1
                )
            } else {
                LoadResult.Error(Exception("Network error"))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FeedData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
