package com.toren.foodbookapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toren.foodbookapp.databinding.RegisterFragmentBinding
import com.toren.foodbookapp.ui.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            textButtonLogin.setOnClickListener {
                actionToLogin()
            }
            buttonRegister.setOnClickListener {
                if (userControl()) {
                    userRegister()
                }
            }
        }

    }

    private fun userControl(): Boolean {
        var control = true

        binding.apply {
            if (inputName.editText!!.text.toString().isEmpty() || inputName.editText!!.text.length < 3) {
                inputName.error = "Geçerli bir isim giriniz."
                control = false
            }
            if (inputSurname.editText!!.text.toString().isEmpty() || inputSurname.editText!!.text.length < 3) {
                inputSurname.error = "Geçerli bir isim giriniz."
                control = false
            }
            if (inputMail.editText!!.text.isEmpty()) {
                inputMail.error = "Geçerli bir email adresi giriniz."
                control = false
            }
            if (inputPassword.editText!!.text.isEmpty() || inputPassword.editText!!.text.length < 6) {
                inputPassword.error = "Minimum 6 karakterli bir parola belirleyiniz."
                control = false
            }
            if (inputPasswordControl.editText!!.text.isEmpty() || inputPasswordControl.editText!!.text.length < 6) {
                inputPasswordControl.error = "Minimum 6 karakterli bir parola belirleyiniz."
                control = false
            }
        }

        return control
    }

    private fun userRegister() {
        Toast.makeText(this.context, "BAŞARILI", Toast.LENGTH_SHORT).show()
    }

    private fun actionToLogin() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment2()
        findNavController().navigate(action)
    }

}