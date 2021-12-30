package com.toren.foodbookapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toren.foodbookapp.ui.viewmodel.LoginViewModel
import com.toren.foodbookapp.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonRegister.setOnClickListener {
                actionToRegister()
            }
            buttonLogin.setOnClickListener {
                if (userControl()) {
                    viewModel.loginAccount(inputEmail.text.toString(), inputPassword.text.toString())
                    loginAccount()
                }
            }
        }

    }

    private fun loginAccount() {
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
            if (inputEmail.text.isEmpty()) {
                inputEmail.error = "Geçerli bir email adresi giriniz."
                control = false
            } else {
                inputEmail.error = null
            }
            if (inputPassword.text.isEmpty() || inputPassword.text.length < 6) {
                inputPassword.error = "Minimum 6 karakterli bir parola belirleyiniz."
                control = false
            } else {
                inputPassword.error = null
            }
        }

        return control
    }

    private fun actionToHome() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment2()
        findNavController().navigate(action)
    }

    private fun actionToRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}