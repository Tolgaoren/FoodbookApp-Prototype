package com.toren.foodbookapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.toren.foodbookapp.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val database: DatabaseReference = Firebase.database.reference

    fun saveNewUser(user: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            database.child("users").child(user.uuid).setValue(user)
        }
    }

}