package com.example.lab1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText

const val EXTRA_USER_NAME = "com.example.lab1.USER_NAME"
const val EXTRA_USER_EMAIL = "com.example.lab1.USER_EMAIL"
const val EXTRA_USER_OBJECT = "com.example.lab1.USER_OBJECT"

class SignUpActivity : AppCompatActivity() {

    private val TAG = "SignUpActivity"

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

        val signUpButton = findViewById<Button>(R.id.buttonSignUpCreate)
        val goToSignInButton = findViewById<Button>(R.id.buttonGoToSignIn)
        val previousButton = findViewById<Button>(R.id.buttonPreviousSignUp)

        signUpButton.setOnClickListener {
            val name = nameEditText?.text.toString()
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

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
                val user = User(name, email, password)

                // для возврата данных
                val resultIntent = Intent()
                // Передача данных с использованием стринга
                resultIntent.putExtra(EXTRA_USER_NAME, name)
                resultIntent.putExtra(EXTRA_USER_EMAIL, email)
                // Передача объекта User
                resultIntent.putExtra(EXTRA_USER_OBJECT, user)

                setResult(Activity.RESULT_OK, resultIntent)
                Toast.makeText(this, "Регистрация успешна для $name", Toast.LENGTH_SHORT).show()
                finish()

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

            finish()
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