package com.toren.foodbookapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.toren.foodbookapp.ui.viewmodel.AddFoodViewModel
import com.toren.foodbookapp.R

class AddFoodFragment : Fragment() {

    private val viewModel: AddFoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_food_fragment, container, false)
    }


}