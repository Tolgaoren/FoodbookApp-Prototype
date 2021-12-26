package com.toren.foodbookapp.ui.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.toren.foodbookapp.databinding.SplashFragmentBinding
import com.toren.foodbookapp.ui.viewmodel.SplashViewModel

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()
    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLogin()

        Handler(Looper.getMainLooper()).postDelayed({ isLogin() }, 2000)

    }

    private fun isLogin() {
        viewModel.isLogin.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    actionToHome()
                } else {
                    actionToLogin()
                }
            }
        })
    }

    private fun actionToLogin() {
        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun actionToHome() {
        val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}