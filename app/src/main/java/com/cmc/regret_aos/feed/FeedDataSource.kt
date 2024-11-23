package com.cmc.regret_aos.feed

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.regret_aos.api.ApiService
import com.cmc.regret_aos.api.UserPreferences

class FeedDataSource(
    private val service: ApiService,
    private val preferences: UserPreferences
) : PagingSource<Int, FeedData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedData> {
        return try {
            val currentPage = params.key ?: 1

//            val response = listOf(
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//                FeedData("content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 content1 "),
//            )
//            LoadResult.Page(
//                data = response,
//                prevKey = if (currentPage == 1) null else currentPage - 1,
//                nextKey = if (response.isEmpty()) null else currentPage + 1
//            )
            val userId = preferences.getUserId()
            val response = service.getFeedDataList(userId, currentPage, params.loadSize)

            if (response.isSuccessful) {
                val pagedResponse = response.body()!!
                LoadResult.Page(
                    data = pagedResponse.regrets,
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
