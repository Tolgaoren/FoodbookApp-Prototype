package com.toren.foodbookapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toren.foodbookapp.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    var control = MutableLiveData(false)

    fun saveNewUser(user: Users, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(user.email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        user.uuid = auth.uid.toString()
                        db.collection("users").document(user.uuid).set(user)
                        Log.d("TAG", "createUserWithEmail:success")
                        control.value = true
                    } else {
                        Log.d("TAG", "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }

}