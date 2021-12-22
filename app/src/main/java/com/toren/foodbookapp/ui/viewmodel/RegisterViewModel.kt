package com.toren.foodbookapp.ui.viewmodel

import android.util.Log
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
    private var control = true

    fun saveNewUser(user: Users, password: String): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(user.email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        user.uuid = auth.uid.toString()
                        db.collection("users").document(user.uuid).set(user)
                        Log.d("TAG", "createUserWithEmail:success")
                    } else {
                        Log.d("TAG", "createUserWithEmail:failure", task.exception)
                        control = false
                    }
                }
        }
        return control
    }

}