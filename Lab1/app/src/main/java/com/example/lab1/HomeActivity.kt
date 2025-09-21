package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // Import Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity" // Define TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d(TAG, "onCreate")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItems)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val carList = listOf(
            Car("Audi A7", "Седан, Быстрый, Немецкое качество", "$25,000", R.drawable.a7),
            Car("RAM", "4x4, Внедорожник, Пикап", "$45,000", R.drawable.ram),
            Car("BMW X7", "Большой Кроссовер, Технологичный, Для большой семьи", "$75,000", R.drawable.x7),
            Car("BMW Z4", "Кабриолет, Юркий и быстрый, Купе", "$18,000", R.drawable.z4),
            Car("Toyota LC", "Японская надежность, Проверен временем, Высокая безоспасность", "$55,000", R.drawable.lc)
        )

        val adapter = CarAdapter(carList)
        recyclerView.adapter = adapter

        val previousButton = findViewById<Button>(R.id.buttonPreviousHome)
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