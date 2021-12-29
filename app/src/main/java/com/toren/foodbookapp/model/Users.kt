package com.toren.foodbookapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    var uuid: String = "",
    val imgUrl: String = "https://firebasestorage.googleapis.com/v0/b/foodbook-33a6d.appspot.com/o/default%2Fdefault_img.jpg?alt=media&token=cd53e727-7a64-48ca-a6d5-f30c769a3963"
) : Parcelable
