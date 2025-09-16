package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout // Добавлен импорт
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

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
                    emailEditText?.error = "Invalid email format"
                    Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    emailEditText?.error = null
                }

                if (password.length < 6) {
                    passwordEditText?.error = "Password should be at least 6 characters"
                    Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    passwordEditText?.error = null
                }

                Toast.makeText(this, "Sign Up Successful for $name", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finishAffinity()

            } else {
                if (name.isEmpty()) {
                    nameEditText?.error = "Name cannot be empty"
                }
                if (email.isEmpty()) {
                    emailEditText?.error = "Email cannot be empty"
                }
                if (password.isEmpty()) {
                    passwordEditText?.error = "Password cannot be empty"
                }
                Toast.makeText(this, "Please fill all mandatory fields", Toast.LENGTH_SHORT).show()
            }
        }

        goToSignInButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        previousButton.setOnClickListener {
            finish()
        }
    }
}