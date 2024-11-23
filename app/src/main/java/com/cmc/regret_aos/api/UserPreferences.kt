package com.cmc.regret_aos.api

import android.content.Context
import javax.inject.Inject

class UserPreferences @Inject constructor(context: Context) {
    private val preferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: String) {
        preferences.edit().putString("user_id", userId).apply()
    }

    fun getUserId(): String? {
        return preferences.getString("user_id", null)
    }

    fun saveBirth(birth: String) {
        preferences.edit().putString("birth", birth).apply()
    }

    fun getBirth(): String? {
        return preferences.getString("birth", null)
    }

    fun saveGender(gender: String) {
        preferences.edit().putString("gender", gender).apply()
    }

    fun getGender(): String? {
        return preferences.getString("gender", null)
    }

    fun saveMajor(major: String) {
        preferences.edit().putString("major", major).apply()
    }

    fun getMajor(): String? {
        return preferences.getString("major", null)
    }

    fun saveField(field: String) {
        preferences.edit().putString("field", field).apply()
    }

    fun getField(): String? {
        return preferences.getString("field", null)
    }

    fun saveSort(sort: String) {
        preferences.edit().putString("sort", sort).apply()
    }

    fun getSort(): String? {
        return preferences.getString("sort", null)
    }

}
