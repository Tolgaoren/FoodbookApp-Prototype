package com.toren.foodbookapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toren.foodbookapp.databinding.MaterialItemBinding

class MaterialItemAdapter(
    private val materialList: ArrayList<String>,
    private val listener: OnItemClickListener,
    private val deleteIconStatus: Boolean
) : RecyclerView.Adapter<MaterialItemAdapter.ViewHolder>() {

    inner class ViewHolder(val materialItemBinding: MaterialItemBinding) :
        RecyclerView.ViewHolder(materialItemBinding.root), View.OnClickListener {
        init {
            materialItemBinding.itemDelete.setOnClickListener(this)
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
        if (!deleteIconStatus) {
            holder.materialItemBinding.itemDelete.visibility = View.INVISIBLE
        }
        holder.materialItemBinding.itemName.text = materialList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MaterialItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return materialList.size
    }

    fun removeItem(position: Int) {
        materialList.removeAt(position)
        notifyDataSetChanged()
    }

    fun updateList(newMaterialList: ArrayList<String>) {
        materialList.clear()
        materialList.addAll(newMaterialList)
        notifyDataSetChanged()
    }

    fun temizle() {
        materialList.clear()
        notifyDataSetChanged()
    }
}
