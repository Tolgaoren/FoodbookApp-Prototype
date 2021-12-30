package com.toren.foodbookapp.ui.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File

class FoodViewModel : ViewModel() {

    private val imageRef = Firebase.storage.reference
    val image = MutableLiveData<Bitmap>()

    fun getBitmap(imgUrl: String) {
        val localFile = File.createTempFile("tempImage", "jpg")
        imageRef.child(imgUrl).getFile(localFile).addOnSuccessListener {
            image.value = BitmapFactory.decodeFile(localFile.absolutePath)
        }
    }

}