package com.cmc.regret_aos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.regret_aos.api.ApiService
import com.cmc.regret_aos.api.UserPreferences
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : ViewModel() {

    fun saveUserId(userId: Long) {
        userPreferences.saveUserId(userId)
    }

    fun getUserId(): Long {
        return userPreferences.getUserId()
    }

    // 추가적인 로그인 관련 로직을 여기에 작성할 수 있습니다.

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password




    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> get() = _gender

    fun setGender(value: String) {
        _gender.value = value
    }

    private val _birthDate = MutableLiveData<String>()
    val birthDate: LiveData<String> get() = _birthDate

    fun setBirth(value: String) {
        _birthDate.value = value
    }

    private val _major = MutableLiveData<String>()
    val major: LiveData<String> get() = _major

    fun setMajor(value: String) {
        _major.value = value
    }

    private val _field = MutableLiveData<String>()
    val field: LiveData<String> get() = _field

    fun setField(value: String) {
        _field.value = value
    }


    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun submitData(lister: (Boolean) -> Unit) {
        viewModelScope.launch {
            val userData = UserData(
                email = _email.value ?: "",
                password = _password.value ?: "",
                gender = _gender.value ?: "",
                birthDate = _birthDate.value ?: "",
                major = _major.value ?: "",
                field = _field.value ?: "",
            )

            val response = apiService.submitUserData(userData)
            if (response.isSuccessful) {
                response.body()?.let {
                    it.data?.let { userResponse ->
                        userResponse.userId?.let { userId ->
                        userPreferences.saveUserData(
                            userId = userId,
                            gender = userData.gender,
                            birth = userData.birthDate,
                            major = userData.major,
                            field = userData.field
                        )
                    }}
                }

                lister(true)
            } else {
                lister(false)
            }
        }
    }
}
data class UserData(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("gender") val gender : String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("major") val major: String,
    @SerializedName("field") val field: String,
)