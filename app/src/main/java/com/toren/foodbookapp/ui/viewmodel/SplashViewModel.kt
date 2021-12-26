package com.toren.foodbookapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: MutableLiveData<Boolean> = _isLogin

    fun isLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            if (auth.currentUser != null) {
                _isLogin.postValue(true)
            } else {
                _isLogin.postValue(false)
            }
        }
    }

}