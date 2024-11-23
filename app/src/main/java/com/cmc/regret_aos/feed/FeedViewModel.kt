package com.cmc.regret_aos.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.cmc.regret_aos.Field
import com.cmc.regret_aos.Gender
import com.cmc.regret_aos.Major
import com.cmc.regret_aos.Sort
import com.cmc.regret_aos.api.ApiService
import com.cmc.regret_aos.api.UserPreferences
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : ViewModel() {

    val feedData = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { FeedDataSource(apiService, userPreferences) }
    ).flow.cachedIn(viewModelScope)

//    fun test() {
//
//        Log.d("testLog","testLog 1")
//        viewModelScope.launch {
//            val userId = 1L // 예시
//            val json = """{"userId":$userId}"""
//            val requestBody = RequestBody.create(MediaType.parse("application/json"), json)
//
//            val response = apiService.getFeedDataList(requestBody, 0, 10)
//            if (response.isSuccessful) {
//
//                Log.d("testLog","success ${response.body()} }")
//                val pagedResponse = response.body()!!
//                pagedResponse.regrets.forEach {
//                    println(it.content)
//                }
//            } else {
//                Log.d("testLog","error ")
//            }
//        }
//    }

    private val _birth = MutableLiveData<String>()
    val birth: LiveData<String> get() = _birth

    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> get() = _gender

    private val _major = MutableLiveData<String>()
    val major: LiveData<String> get() = _major

    private val _field = MutableLiveData<String>()
    val field: LiveData<String> get() = _field

    private val _sort = MutableLiveData<String>()
    val sort: LiveData<String> get() = _sort

    init {
        getAllData()
    }

    private fun getAllData() {
        _birth.value = userPreferences.getBirth()
        _gender.value = userPreferences.getGender()
        _major.value = userPreferences.getMajor()
        _field.value = userPreferences.getField()
        _sort.value = userPreferences.getSort()
    }

    fun saveBirth(value: String) {
        _birth.value = value
        userPreferences.saveBirth(value)
    }

    fun saveGender(value: String) {
        _gender.value = value
        userPreferences.saveGender(value)
    }

    fun saveMajor(value: String) {
        _major.value = value
        userPreferences.saveMajor(value)
    }

    fun saveField(value: String) {
        _field.value = value
        userPreferences.saveField(value)
    }

    fun saveSort(value: String) {
        _sort.value = value
        userPreferences.saveSort(value)
    }
}

