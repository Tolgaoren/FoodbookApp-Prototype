package com.toren.foodbookapp.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.toren.foodbookapp.model.Yemek
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFoodViewModel : ViewModel() {

    private val database: DatabaseReference = Firebase.database.reference
    private val storegeReference: StorageReference = Firebase.storage.reference

    fun saveNewFood(yemek: Yemek, imageUrl: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            storegeReference.child(yemek.user+yemek.yemekIsmi).putFile(imageUrl)
            // database.child("foods").child(yemek.user+yemek.yemekIsmi).setValue(yemek)
            database.child("foods").push().setValue(yemek)
        }
    }

}