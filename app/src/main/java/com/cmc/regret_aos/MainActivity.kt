package com.cmc.regret_aos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.v2.auth.BuildConfig

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "MainActivity"

        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)

        val kakaoLoginButton = findViewById<ImageButton>(R.id.kakao_login_btn)
        kakaoLoginButton.setOnClickListener {
            UserApiClient.instance.loginWithKakaoAccount(this) { user, error ->
                if (error != null) {
                    Log.e(TAG, "카카오 로그인 실패", error)
                } else if (user != null) {
                    Log.i(TAG, "카카오 로그인 성공 ${user}")

                    // 액세스 토큰을 이용하여 사용자 정보 가져오기
                }
            }
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this) && intent?.data != null) {
            // 카카오톡으로 로그인 한 경우
            handleToken(intent)
        }
    }

    private fun handleToken(intent: Intent) {
//        UserApiClient.instance.accessTokenInfo()
//        val token = UserApiClient.instance.tokenManager.getAccessToken()
        // 토큰을 이용하여 사용자 정보 가져오기
    }
}