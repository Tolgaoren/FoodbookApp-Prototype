package com.toren.foodbookapp.ui.viewmodel

import android.util.Log
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
    val eslesenYemekler = MutableLiveData<List<Yemek>>()
    private val esle = ArrayList<Yemek>()

    fun yemekleriGetir(malzemeler: Set<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = db.collection("foods")
            data.get().addOnSuccessListener {
                if (it != null) {
                    foodList.value = it.toObjects(Yemek::class.java)
                    yemekleriEslestir(malzemeler)
                }
            }.addOnFailureListener {

            }
        }
    }

    fun yemekleriEslestir(malzemeler: Set<String>) {
        val yemekler = foodList.value!!.toList()
        for (i in yemekler) {
            if (malzemeler.containsAll(i.malzemeler)) {
                esle.add(i)
            }
        }
        eslesenYemekler.value = esle
        Log.d("TAG","Boy" + esle.size.toString())
        Log.d("TAG","Malzeme" + malzemeler.size.toString())
    }


}