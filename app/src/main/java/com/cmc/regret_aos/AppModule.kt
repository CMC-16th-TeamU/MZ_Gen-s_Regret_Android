package com.cmc.regret_aos

import android.content.Context
import com.cmc.regret_aos.api.AuthInterceptor
import com.cmc.regret_aos.api.UserIdInterceptor
import com.cmc.regret_aos.api.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    fun provideUserIdInterceptor(userPreferences: UserPreferences): UserIdInterceptor {
        return UserIdInterceptor(userPreferences)
    }

}
