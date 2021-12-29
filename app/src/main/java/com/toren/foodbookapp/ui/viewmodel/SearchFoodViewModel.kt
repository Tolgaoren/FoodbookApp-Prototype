package com.toren.foodbookapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toren.foodbookapp.model.Yemek
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFoodViewModel : ViewModel() {

    private val db = Firebase.firestore
    val foodList = MutableLiveData<List<Yemek>>()

    fun yemekleriGetir() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = db.collection("foods")
            data.get().addOnSuccessListener {
                if (it != null) {
                    foodList.value = it.toObjects(Yemek::class.java)
                }
            }.addOnFailureListener {

            }
        }
    }

}