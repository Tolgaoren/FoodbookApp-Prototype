package com.toren.foodbookapp.model

data class Users(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val following: ArrayList<String> = arrayListOf(),
    val uuid: String = ""
)
