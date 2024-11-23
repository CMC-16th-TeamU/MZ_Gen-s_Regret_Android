package com.cmc.regret_aos.api

import com.cmc.regret_aos.Gender
import com.cmc.regret_aos.UserData
import com.cmc.regret_aos.feed.FeedData
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("regrets/users/{userId}")
    suspend fun getFeedDataList(
        @Path("userId") userId: Long,
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): Response<ApiResponse<PagedResponse<FeedData>>>

    @POST("users")
    @Headers("Content-Type: application/json")
    suspend fun submitUserData(
        @Body data: UserData
    ): Response<ApiResponse<userResponse>>
}
data class ApiResponse<T>( @SerializedName("code") val code: Int, @SerializedName("message") val message: String, @SerializedName("data") val data: T )
data class userResponse(
    @SerializedName("id") val userId: Long,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("birthDate") val birthDate: String,
)
