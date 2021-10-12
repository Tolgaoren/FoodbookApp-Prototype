package com.toren.foodbookapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toren.foodbookapp.databinding.MaterialItemBinding
import com.toren.foodbookapp.model.Material

class MaterialAdapter(private val materialList: ArrayList<Material>) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {
    class ViewHolder(val materialItemBinding: MaterialItemBinding) : RecyclerView.ViewHolder(materialItemBinding.root) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.materialItemBinding.itemName.text = materialList[position].materialName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MaterialItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return materialList.size
    }

    fun updateList(newMaterialList: ArrayList<Material>) {
        materialList.clear()
        materialList.addAll(newMaterialList)
        notifyDataSetChanged()
    }
}
