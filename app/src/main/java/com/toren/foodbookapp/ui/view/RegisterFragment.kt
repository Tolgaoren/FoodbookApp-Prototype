package com.toren.foodbookapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toren.foodbookapp.databinding.RegisterFragmentBinding
import com.toren.foodbookapp.model.Users
import com.toren.foodbookapp.ui.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    private var _binding: RegisterFragmentBinding? = null
    private val binding get () = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(layoutInflater)
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
                    val user = Users(
                        name = binding.inputName.editText!!.text.toString(),
                        surname = binding.inputSurname.editText!!.text.toString(),
                        email = binding.inputMail.editText!!.text.toString(),
                    )
                    viewModel.saveNewUser(user, inputPassword.editText!!.text.toString())
                    saveNewUser()
                }
            }
        }

    }

    private fun saveNewUser() {
        viewModel.control.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    actionToHome()
                }
            }
        })
    }

    private fun userControl(): Boolean {
        var control = true

        binding.apply {
            if (inputName.editText!!.text.toString()
                    .isEmpty() || inputName.editText!!.text.length < 3
            ) {
                inputName.error = "Geçerli bir isim giriniz."
                control = false
            } else {
                inputName.error = null
            }
            if (inputSurname.editText!!.text.toString()
                    .isEmpty() || inputSurname.editText!!.text.length < 3
            ) {
                inputSurname.error = "Geçerli bir isim giriniz."
                control = false
            } else {
                inputSurname.error = null
            }
            if (inputMail.editText!!.text.isEmpty()) {
                inputMail.error = "Geçerli bir email adresi giriniz."
                control = false
            } else {
                inputMail.error = null
            }
            if (inputPassword.editText!!.text.isEmpty() || inputPassword.editText!!.text.length < 6) {
                inputPassword.error = "Minimum 6 karakterli bir parola belirleyiniz."
                control = false
            } else {
                inputPassword.error = null
            }
            if (inputPasswordControl.editText!!.text.toString() != inputPassword.editText!!.text.toString()) {
                inputPasswordControl.error = "Lütfen parolayı aynı giriniz."
                control = false
            } else {
                inputPasswordControl.error = null
            }
        }

        return control
    }

    private fun actionToLogin() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment2()
        findNavController().navigate(action)
    }

    private fun actionToHome() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment2()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}