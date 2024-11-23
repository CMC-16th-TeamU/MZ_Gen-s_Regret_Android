package com.cmc.regret_aos.api

import com.cmc.regret_aos.feed.FeedData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("endpoint")
    suspend fun getFeedDataList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<PagedResponse<FeedData>>
}
