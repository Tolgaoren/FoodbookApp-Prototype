package com.toren.foodbookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.toren.foodbookapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbarm))

        val navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> hideBottomBar()
                R.id.registerFragment -> hideBottomBar()
                R.id.addFoodFragment -> hideBottomBar()
                R.id.splashFragment -> hideBottomBar()
                else -> showBottomBar()
            }
        }
    }

    private fun showBottomBar() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomBar() {
        binding.bottomNavigationView.visibility = View.GONE
    }

}