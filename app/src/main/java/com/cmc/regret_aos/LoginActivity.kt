package com.cmc.regret_aos

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.cmc.regret_aos.databinding.ActivityLoginBinding
import android.util.Patterns

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailTextView = findViewById<TextView>(R.id.emailTextView) // 이메일 결과 표시용 TextView
        val ageTextView = findViewById<TextView>(R.id.ageTextView) // 나이를 표시하는 TextView
        val editText = findViewById<EditText>(R.id.editText) // 입력 EditText
        val logButton = findViewById<Button>(R.id.logButton) // 문자열 출력 버튼
        val maxLength = 1000
        var isTextUpdating = false // 무한 루프 방지 플래그

        // 1. 다이얼로그로 이메일 입력받기
        emailTextView.setOnClickListener {
            showEmailInputDialog(emailTextView)
        }

        // 2. 다이얼로그로 나이 입력받기
        ageTextView.setOnClickListener {
            showAgeInputDialog(ageTextView)
        }

        // 3. 문자열 입력 제한
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isTextUpdating) { // 무한 루프 방지
                    if (s != null && s.length > maxLength) {
                        Toast.makeText(
                            this@LoginActivity,
                            "입력은 최대 $maxLength 길이 까지 가능합니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        isTextUpdating = true
                        editText.setText(s.subSequence(0, maxLength)) // 1000자 초과 시 잘라냄
                        editText.setSelection(maxLength) // 커서를 끝으로 이동
                        isTextUpdating = false
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // 4. 버튼 클릭 시 문자열 로그 출력
        logButton.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isNotEmpty()) {
                Log.d("test_debug", "User Input: $inputText")
                Toast.makeText(this, "로그에 출력되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "문자열을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 이메일 입력 다이얼로그
    private fun showEmailInputDialog(emailTextView: TextView) {
        val input = EditText(this)
        input.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS // 이메일 입력 형식
        input.hint = "이메일을 입력하세요"

        val dialog = AlertDialog.Builder(this)
            .setTitle("이메일 입력")
            .setMessage("이메일을 입력해주세요:")
            .setView(input)
            .setPositiveButton("확인") { _, _ ->
                val email = input.text.toString()
                if (isValidEmail(email)) {
                    emailTextView.text = "이메일: $email"
                    Toast.makeText(this, "유효한 이메일입니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "유효하지 않은 이메일입니다.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // 나이 입력 다이얼로그
    private fun showAgeInputDialog(ageTextView: TextView) {
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER // 숫자만 입력 가능
        input.hint = "나이를 입력하세요"

        val minAge = 1
        val maxAge = 120
        val dialog = AlertDialog.Builder(this)
            .setTitle("나이 입력")
            .setMessage("나이를 입력해주세요:")
            .setView(input)
            .setPositiveButton("확인") { _, _ ->
                val age = input.text.toString()
                if (age.isNotEmpty() && age.toIntOrNull() != null) {
                    if (age.toInt() in minAge..maxAge) { // 나이 유효성 검사 1 ~ 120
                        ageTextView.text = "나이: $age"
                    } else {
                        Toast.makeText(this, "유효하지 않은 나이입니다(1살 ~ 120살 사이 입력).", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this, "나이를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}