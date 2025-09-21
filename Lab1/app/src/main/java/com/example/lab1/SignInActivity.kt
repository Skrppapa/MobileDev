package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // Import Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout // Добавлен импорт
import com.google.android.material.textfield.TextInputEditText

class SignInActivity : AppCompatActivity() {

    private val TAG = "SignInActivity" // Define TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        Log.d(TAG, "onCreate")

        val emailInputLayout = findViewById<TextInputLayout>(R.id.textFieldEmail)
        val emailEditText = emailInputLayout.editText

        val passwordInputLayout = findViewById<TextInputLayout>(R.id.textFieldPassword)
        val passwordEditText = passwordInputLayout.editText
        
        val signInButton = findViewById<Button>(R.id.buttonSignIn)
        val goToSignUpButton = findViewById<Button>(R.id.buttonGoToSignUp)
        val previousButton = findViewById<Button>(R.id.buttonPrevious)

        signInButton.setOnClickListener {
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                var signedIn = false
                if (email == "test@example.com" && password == "password") {
                    signedIn = true
                    Toast.makeText(this, "Успешная авторизация", Toast.LENGTH_SHORT).show()
                } else if (email.contains("@") && email.length > 5 && password.length >= 6) {
                    signedIn = true
                    Toast.makeText(this, "Sign In Successful (any non-empty valid format)", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Неверный формат email или пароля", Toast.LENGTH_SHORT).show()
                }

                if (signedIn) {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finishAffinity()
                }
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        goToSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
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