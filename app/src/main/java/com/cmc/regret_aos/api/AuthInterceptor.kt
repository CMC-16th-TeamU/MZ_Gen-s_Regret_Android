package com.cmc.regret_aos.api

import okhttp3.Interceptor
import okhttp3.Response

class UserIdInterceptor(private val userPreferences: UserPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
//        val userId = userPreferences.getUserId()

        val newRequest = originalRequest.newBuilder()
            .build()
        return chain.proceed(newRequest)
    }
}
