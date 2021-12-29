package com.toren.foodbookapp.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.toren.foodbookapp.databinding.FoodItemBinding
import com.toren.foodbookapp.model.Yemek
import java.io.File

class FoodItemAdapter(
    private val foodList: ArrayList<Yemek>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FoodItemAdapter.ViewHolder>() {

    private val imageRef = Firebase.storage.reference

    inner class ViewHolder(val foodItemBinding: FoodItemBinding) : RecyclerView.ViewHolder(foodItemBinding.root),
        View.OnClickListener {
        init {
            foodItemBinding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodItemBinding.foodName.text = foodList[position].yemekIsmi
        val localFile = File.createTempFile("tempImage", "jpg")
        imageRef.child(foodList[position].imgUrl).getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            holder.foodItemBinding.foodImage.load(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateList(newFoodList: ArrayList<Yemek>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }

    fun updateList(newFoodList: List<Yemek>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}