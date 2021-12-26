package com.toren.foodbookapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toren.foodbookapp.databinding.MaterialItemBinding

class MaterialItemAdapter(private val materialList: ArrayList<String>) : RecyclerView.Adapter<MaterialItemAdapter.ViewHolder>() {
    class ViewHolder(val materialItemBinding: MaterialItemBinding) : RecyclerView.ViewHolder(materialItemBinding.root) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.materialItemBinding.itemName.text = materialList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MaterialItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return materialList.size
    }

    fun updateList(newMaterialList: ArrayList<String>) {
        materialList.clear()
        materialList.addAll(newMaterialList)
        notifyDataSetChanged()
    }
}
