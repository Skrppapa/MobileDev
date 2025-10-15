package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Список авто
data class Car(val name: String, val description: String, val price: String, val imageResId: Int? = null)

class CarAdapter(private val carList: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() { // Адаптер для списка
    //  RecyclerView.Adapter — базовый класс для предоставления данных и создания представлений

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder { // Метод для создания нового ViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val currentItem = carList[position]
        holder.carName.text = currentItem.name
        holder.carDescription.text = currentItem.description
        holder.carPrice.text = currentItem.price
        currentItem.imageResId?.let {
            holder.carImage.setImageResource(it)
        } ?: run {
            holder.carImage.setImageResource(R.drawable.ic_launcher_foreground) // Если изображение не завезли - используется заглушка
        }
    }

    override fun getItemCount() = carList.size

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {  // Внутренний класс, наследуется от RecyclerView.ViewHolder
        val carImage: ImageView = itemView.findViewById(R.id.imageViewCarItem) // ViewHolder описывает представление элемента и метаданные о его месте в RecyclerView
        val carName: TextView = itemView.findViewById(R.id.textViewCarName) // itemView: View: Представление одного элемента списка
        val carDescription: TextView = itemView.findViewById(R.id.textViewCarDescription)
        val carPrice: TextView = itemView.findViewById(R.id.textViewCarPrice)
    }
}