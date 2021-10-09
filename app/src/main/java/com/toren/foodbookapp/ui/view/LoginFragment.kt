package com.toren.foodbookapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toren.foodbookapp.ui.viewmodel.LoginViewModel
import com.toren.foodbookapp.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonRegister.setOnClickListener {
                actionToRegister()
            }
            buttonLogin.setOnClickListener {
                actionToHome()
            }
        }

    }

    private fun actionToHome() {

    }

    private fun actionToRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment2()
        findNavController().navigate(action)
    }


}