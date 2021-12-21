package com.toren.foodbookapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.toren.foodbookapp.R

class HomeRecyclerAdapter(private val tarifBaslikArray: ArrayList<String>,
                          private val tarifFotoArray: ArrayList<String>,
private val tarifAciklamaArray: ArrayList<String>)
    : RecyclerView.Adapter<HomeRecyclerAdapter.PostHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =  inflater.inflate(R.layout.recycler_view_row, parent,false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.rec_baslik_view?.text = tarifBaslikArray[position]
        holder.rec_aciklama_view?.text = tarifAciklamaArray[position]
        Picasso.get().load(tarifFotoArray[position]).resize(900,1200).centerCrop().into(holder.rec_image_view)
    }

    override fun getItemCount(): Int {
        return tarifBaslikArray.size

    }
    class PostHolder(view : View) : RecyclerView.ViewHolder(view) {
        var rec_baslik_view : TextView? = null
        var rec_image_view : ImageView? = null
        var rec_aciklama_view :  TextView? = null
        init {
            rec_baslik_view = view.findViewById(R.id.rec_baslik_view)
            rec_image_view = view.findViewById(R.id.rec_image_view)
            rec_aciklama_view = view.findViewById(R.id.rec_aciklama_view)
        }
    }
}