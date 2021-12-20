package com.toren.foodbookapp.model

data class Yemek(
    val yemekIsmi: String,
    val malzemeler: ArrayList<String>,
    val malzemelerDetayli: ArrayList<Material>,
    val hazirlanis: String,
    val kategori: String,
    val imgUrl: String,
    val user: String
)
