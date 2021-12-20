package com.toren.foodbookapp.model

data class Yemek(
    val yemekIsmi: String,
    val malzemeler: ArrayList<String>,
    val malzemelerDetayli: ArrayList<String>,
    val hazirlanis: String,
    val kategori: String,
    val imgUrl: String,
    val user: String
)
