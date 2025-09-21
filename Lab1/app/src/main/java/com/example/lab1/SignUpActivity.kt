package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // Import Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout // Добавлен импорт
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {

    private val TAG = "SignUpActivity" // Define TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        Log.d(TAG, "onCreate")

        val nameInputLayout = findViewById<TextInputLayout>(R.id.textFieldName)
        val nameEditText = nameInputLayout.editText

        val emailInputLayout = findViewById<TextInputLayout>(R.id.textFieldEmailSignUp)
        val emailEditText = emailInputLayout.editText

        val passwordInputLayout = findViewById<TextInputLayout>(R.id.textFieldPasswordSignUp)
        val passwordEditText = passwordInputLayout.editText

        // val ageInputLayout = findViewById<TextInputLayout>(R.id.textFieldAge) // Пример для опционального поля

        val signUpButton = findViewById<Button>(R.id.buttonSignUpCreate)
        val goToSignInButton = findViewById<Button>(R.id.buttonGoToSignIn)
        val previousButton = findViewById<Button>(R.id.buttonPreviousSignUp)

        signUpButton.setOnClickListener {
            val name = nameEditText?.text.toString()
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            // val age = ageEditText?.text.toString() // Пример для опционального поля

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText?.error = "Некорректный формат email"
                    Toast.makeText(this, "Некорректный формат email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    emailEditText?.error = null
                }

                if (password.length < 6) {
                    passwordEditText?.error = "Пароль должен содержать не менее 6 символов"
                    Toast.makeText(this, "Пароль должен содержать не менее 6 символов", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    passwordEditText?.error = null
                }

                Toast.makeText(this, "Необходимо зарегистрироваться для получения $name", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finishAffinity()

            } else {
                if (name.isEmpty()) {
                    nameEditText?.error = "Имя не может быть пустым"
                }
                if (email.isEmpty()) {
                    emailEditText?.error = "Email не может быть пустым"
                }
                if (password.isEmpty()) {
                    passwordEditText?.error = "Пароль не может быть пустым"
                }
                Toast.makeText(this, "Пожалуйста, заполните все обязательные поля", Toast.LENGTH_SHORT).show()
            }
        }

        goToSignInButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        previousButton.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}