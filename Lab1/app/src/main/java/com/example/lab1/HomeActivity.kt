package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // Import Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() { // HomeActivity является экраном в нашем приложении

    private val TAG = "HomeActivity" // Используется в качестве метки для сообщений в Logcat, помогает фильтровать логи, относящиеся именно к этой Activity.

    override fun onCreate(savedInstanceState: Bundle?) { // Вызывается когда Activity создается впервые
        super.onCreate(savedInstanceState) // Вызывает реализацию этого метода из родительского класса (AppCompatActivity). Обязательный вызов!
        setContentView(R.layout.activity_home) // Ссылаемся на наш шаблон
        Log.d(TAG, "onCreate")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItems) // Находим шаблон по ID
        recyclerView.layoutManager = LinearLayoutManager(this) // LinearLayoutManager означает, что элементы списка будут располагаться друг под другом (вертикально по умолчанию)

        val carList = listOf(
            Car("Audi A7", "Седан, Быстрый, Немецкое качество", "$25,000", R.drawable.a7),
            Car("RAM", "4x4, Внедорожник, Пикап", "$45,000", R.drawable.ram),
            Car("BMW X7", "Большой Кроссовер, Технологичный, Для большой семьи", "$75,000", R.drawable.x7),
            Car("BMW Z4", "Кабриолет, Юркий и быстрый, Купе", "$18,000", R.drawable.z4),
            Car("Toyota LC", "Японская надежность, Проверен временем, Высокая безоспасность", "$55,000", R.drawable.lc)
        )

        val adapter = CarAdapter(carList) // Создает экземпляр CarAdapter, передавая ему список carList.
        recyclerView.adapter = adapter

        val previousButton = findViewById<Button>(R.id.buttonPreviousHome) // Находит кнопку по ID и подписывается на нее отслеживать нажатие
        previousButton.setOnClickListener {
            val intent = Intent(this, OnboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finishAffinity()
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