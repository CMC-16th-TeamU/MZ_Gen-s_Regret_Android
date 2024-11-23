package com.cmc.regret_aos.feed

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cmc.regret_aos.api.ApiService
import com.cmc.regret_aos.api.UserPreferences
import okhttp3.MediaType
import okhttp3.RequestBody

class FeedDataSource(
    private val service: ApiService,
    private val preferences: UserPreferences
) : PagingSource<Int, FeedData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedData> {
        return try {
            val currentPage = params.key ?: 0

            val userId = preferences.getUserId()

            Log.d("testLog", "start")

            val response = service.getFeedDataList(userId, currentPage, params.loadSize)

            Log.d("testLog", "response: ${response}")
            if (response.isSuccessful) {
                val customResponse = response.body()!!
                Log.d("testLog", "customResponse: ${customResponse}")
                val pagedResponse = customResponse.data
                LoadResult.Page(
                    data = pagedResponse.regrets,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (currentPage == pagedResponse.totalPage) null else currentPage + 1
                )
            } else {

                Log.d("testLog", "fail ${response.errorBody()}")
                Log.d("testLog", "fail ${response.body()}")
                LoadResult.Error(Exception("Network error"))
            }
        } catch (exception: Exception) {
            Log.d("testLog", "exception: ${exception}")
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
