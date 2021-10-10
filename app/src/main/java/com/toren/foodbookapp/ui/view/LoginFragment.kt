package com.toren.foodbookapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.toren.foodbookapp.ui.viewmodel.LoginViewModel
import com.toren.foodbookapp.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragmentBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.apply {
            buttonRegister.setOnClickListener {
                actionToRegister()
            }
            buttonLogin.setOnClickListener {
                if (userControl()) {
                    loginAccount(inputEmail.text.toString(), inputPassword.text.toString())
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            actionToHome()
        }
    }

    private fun loginAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    actionToHome()
                } else {
                    Log.d("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this.context, "Giriş bilgileri yanlış.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun userControl(): Boolean {
        var control = true

        binding.apply {
            if (inputEmail.text.isEmpty()) {
                inputEmail.error = "Geçerli bir email adresi giriniz."
                control = false
            } else {
                inputEmail.error = null
            }
            if (inputPassword.text.isEmpty() || inputPassword.text.length < 6) {
                inputPassword.error = "Geçersiz şifre girdiniz."
                control = false
            } else {
                inputPassword.error = null
            }
        }

        return control
    }

    private fun actionToRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()
        findNavController().navigate(action)
    }

    private fun actionToHome() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment2()
        findNavController().navigate(action)
    }

}