package com.toren.foodbookapp.ui.viewmodel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    /*private val db = Firebase.firestore
    val yemekler = MutableLiveData<List<Yemek>>()

    fun searchFoods(queryString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = db.collection("foods").whereArrayContains("yemekIsmi", queryString.trim()).limit(10)
            data.get().addOnSuccessListener {
                if (it != null) {
                    yemekler.value = it.toObjects(Yemek::class.java)
                }
            }.addOnFailureListener() {

            }
        }
    }*/

}