package com.example.lab1

import java.io.Serializable

data class User(
    val name: String,
    val email: String,
    val password: String // Пароль добавлен согласно заданию, хотя в реальных приложениях его так передавать не рекомендуется
) : Serializable