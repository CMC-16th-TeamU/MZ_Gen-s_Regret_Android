package com.cmc.regret_aos.api

import android.content.Context

class UserPreferences(context: Context) {
    private val preferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: String) {
        preferences.edit().putString("user_id", userId).apply()
    }

    fun getUserId(): String? {
        return preferences.getString("user_id", null)
    }
}
