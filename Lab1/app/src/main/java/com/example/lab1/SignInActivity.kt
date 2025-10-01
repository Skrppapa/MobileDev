package com.example.lab1

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // Import Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout // Добавлен импорт
import com.google.android.material.textfield.TextInputEditText

private const val SIGN_UP_REQUEST_CODE = 1

class SignInActivity : AppCompatActivity() {

    private val TAG = "SignInActivity" // Define TAG
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        Log.d(TAG, "onCreate")

        val emailInputLayout = findViewById<TextInputLayout>(R.id.textFieldEmail)
        emailEditText = emailInputLayout.editText as TextInputEditText // Исправлено: явное приведение типа

        val passwordInputLayout = findViewById<TextInputLayout>(R.id.textFieldPassword)
        passwordEditText = passwordInputLayout.editText as TextInputEditText // Исправлено: явное приведение типа
        
        val signInButton = findViewById<Button>(R.id.buttonSignIn)
        val goToSignUpButton = findViewById<Button>(R.id.buttonGoToSignUp)
        val previousButton = findViewById<Button>(R.id.buttonPrevious)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                var signedIn = false
                // Логика входа, в данном случае, если пришли данные с SignUpActivity, можно их использовать
                // Но для примера оставим существующую логику, но можно добавить проверку зарегистрированного пользователя
                if (email == "test@example.com" && password == "password") {
                    signedIn = true
                    Toast.makeText(this, "Успешная авторизация (предустановленный)", Toast.LENGTH_SHORT).show()
                } else if (email.contains("@") && email.length > 5 && password.length >= 6) {
                    signedIn = true
                    // Здесь можно было бы проверить, что email и password совпадают с полученными из SignUpActivity
                    Toast.makeText(this, "Вход выполнен успешно (общая проверка)", Toast.LENGTH_SHORT).show()
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
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, SIGN_UP_REQUEST_CODE)
        }

        previousButton.setOnClickListener {
            finish() 
        }
    }

    @Deprecated("Deprecated in Java") // Добавляем аннотацию, если используем старый метод
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_UP_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                // 1. Получение данных с использованием стандартного типа (String)
                val name = it.getStringExtra(EXTRA_USER_NAME)
                val email = it.getStringExtra(EXTRA_USER_EMAIL)
                
                Toast.makeText(this, "Регистрация: Имя (String): $name, Email (String): $email", Toast.LENGTH_LONG).show()

                // Заполняем поля из полученных данных
                emailEditText.setText(email)
                // passwordEditText.setText(password) // Пароль обычно не предзаполняют после регистрации для безопасности

                // 2. Получение объекта User (Serializable)
                // Используем правильный метод в зависимости от версии SDK для getSerializableExtra
                val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getSerializableExtra(EXTRA_USER_OBJECT, User::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    it.getSerializableExtra(EXTRA_USER_OBJECT) as? User
                }

                user?.let {
                    Toast.makeText(this, "Регистрация (Объект User): Имя: ${it.name}, Email: ${it.email}, Пароль: ${it.password}", Toast.LENGTH_LONG).show()
                    // Можно дополнительно предзаполнить поля, если еще не сделано
                    // emailEditText.setText(it.email)
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