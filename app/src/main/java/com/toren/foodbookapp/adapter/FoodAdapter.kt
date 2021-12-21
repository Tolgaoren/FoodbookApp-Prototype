package com.toren.foodbookapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.toren.foodbookapp.databinding.AccountFoodItemBinding
import com.toren.foodbookapp.model.Yemek

class FoodAdapter(private val foodList: ArrayList<Yemek>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    val imageRef = Firebase.storage.reference

    class ViewHolder(val foodItemBinding: AccountFoodItemBinding) : RecyclerView.ViewHolder(foodItemBinding.root) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodItemBinding.foodText.text = foodList[position].yemekIsmi
        holder.foodItemBinding.foodHazirlanis.text = foodList[position].hazirlanis
        val image = imageRef.child("images\${foodList[position].imgUrl}").downloadUrl.addOnSuccessListener {
            holder.foodItemBinding.foodImage.load(it.toString())
            Log.e("TAG","${it.toString()}WER")
        }
        //holder.foodItemBinding.foodImage.load(image.toString())
        //Log.e("TAG",image.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AccountFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
}