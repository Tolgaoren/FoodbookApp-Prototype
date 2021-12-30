package com.toren.foodbookapp.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.toren.foodbookapp.model.Yemek
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFoodViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val storegeReference: StorageReference = Firebase.storage.reference

    fun saveNewFood(yemek: Yemek, imageUrl: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            storegeReference.child(yemek.yemekIsmi + "?user=" + yemek.user).putFile(imageUrl)
            db.collection("foods").document(yemek.yemekIsmi + "?user=" + yemek.user).set(yemek)
        }
    }

}

