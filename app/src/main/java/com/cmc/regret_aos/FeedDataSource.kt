package com.cmc.regret_aos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.util.Date

class FeedDataSource(
//    private val service: ApiService
) : PagingSource<Int, FeedData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedData> {
        return try {
            val page = params.key ?: 1
//            val response = service.getFeedList(page)

            // dummy data
            val response = listOf(
                FeedData(
                    author = "author",
                    content = "아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회... 아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회...",
                    created = Date()
                ),
                FeedData(
                    author = "author",
                    content = "아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회... 아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회...",
                    created = Date()
                ),
                FeedData(
                    author = "author",
                    content = "아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회... 아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회...",
                    created = Date()
                ),
                FeedData(
                    author = "author",
                    content = "아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회... 아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회...",
                    created = Date()
                ),
                FeedData(
                    author = "author",
                    content = "아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회... 아 진짜 대외활동 안한거 너무 후회돼ㅠ CMC 할껄 왜 안했지 진짜아아아아아아 진짜 대외활동 안한거 너무 후회...",
                    created = Date()
                )
            )


            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
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