package com.example.lab1

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText

private const val SIGN_UP_REQUEST_CODE = 1

class SignInActivity : AppCompatActivity() {

    private val TAG = "SignInActivity"
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private var registeredUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        Log.d(TAG, "onCreate")

        val emailInputLayout = findViewById<TextInputLayout>(R.id.textFieldEmail)
        emailEditText = emailInputLayout.editText as TextInputEditText

        val passwordInputLayout = findViewById<TextInputLayout>(R.id.textFieldPassword)
        passwordEditText = passwordInputLayout.editText as TextInputEditText

        val signInButton = findViewById<Button>(R.id.buttonSignIn)
        val goToSignUpButton = findViewById<Button>(R.id.buttonGoToSignUp)
        val previousButton = findViewById<Button>(R.id.buttonPrevious)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val signedIn: Boolean

                // Сначала проверяем зарегистрированного пользователя
                if (registeredUser != null && email == registeredUser?.email && password == registeredUser?.password) {
                    signedIn = true
                    Toast.makeText(this, "Вход выполнен успешно для ${registeredUser?.name}", Toast.LENGTH_SHORT).show()
                }
                // Затем проверяем предустановленного пользователя
                else if (email == "test@example.com" && password == "password") {
                    signedIn = true
                    Toast.makeText(this, "Успешная авторизация (предустановленный)", Toast.LENGTH_SHORT).show()
                }
                // Если ни один не подошел
                else {
                    signedIn = false
                    Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
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
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, SIGN_UP_REQUEST_CODE)
        }

        previousButton.setOnClickListener {
            finish()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_UP_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                // Получение данных с использованием String
                val name = it.getStringExtra(EXTRA_USER_NAME)
                val email = it.getStringExtra(EXTRA_USER_EMAIL)

                Toast.makeText(this, "Регистрация: Имя (String): $name, Email (String): $email",
                    Toast.LENGTH_LONG).show()

                emailEditText.setText(email)
                // passwordEditText.setText(password) // Пароль не предзаполнчяем для безопасности

                // Получение объекта User (Serializable)
                val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getSerializableExtra(EXTRA_USER_OBJECT, User::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    it.getSerializableExtra(EXTRA_USER_OBJECT) as? User
                }

                user?.let {
                    registeredUser = it
                    Toast.makeText(this, "Регистрация (Объект User): Имя: ${it.name}, Email: ${it.email}, Пароль: ${it.password}", Toast.LENGTH_LONG).show()
                }
            }
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