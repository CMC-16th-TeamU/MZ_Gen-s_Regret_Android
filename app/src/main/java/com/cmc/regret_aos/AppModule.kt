package com.cmc.regret_aos

import com.cmc.regret_aos.api.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccessToken(): String {
        // 여기에 실제로 토큰을 반환하는 로직을 추가합니다. 예: SharedPreferences에서 가져오기
        return "your_access_token_here"
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(accessToken: String): AuthInterceptor {
        return AuthInterceptor(accessToken)
    }
}
