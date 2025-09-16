package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Car(val name: String, val description: String, val price: String, val imageResId: Int? = null)

class CarAdapter(private val carList: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
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
            holder.carImage.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    override fun getItemCount() = carList.size

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImage: ImageView = itemView.findViewById(R.id.imageViewCarItem)
        val carName: TextView = itemView.findViewById(R.id.textViewCarName)
        val carDescription: TextView = itemView.findViewById(R.id.textViewCarDescription)
        val carPrice: TextView = itemView.findViewById(R.id.textViewCarPrice)
    }
}