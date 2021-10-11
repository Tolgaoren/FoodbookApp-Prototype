package com.toren.foodbookapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import com.toren.foodbookapp.ui.viewmodel.AddFoodViewModel
import com.toren.foodbookapp.R
import com.toren.foodbookapp.databinding.AddFoodFragmentBinding

class AddFoodFragment : Fragment() {

    private val viewModel: AddFoodViewModel by viewModels()
    private lateinit var binding: AddFoodFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddFoodFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val materialTypeItems = listOf("adet", "ml", "gr", "kg", "su bardağı", "yemek kaşığı", "tutam", "çay kaşığı")
        val materialTypeAdapter = ArrayAdapter(requireContext(), R.layout.list_item, materialTypeItems)
        (binding.inputMaterialType.editText as AutoCompleteTextView).setAdapter(materialTypeAdapter)

        val foodCategoryItems = listOf(
            "Kahvaltılık Tarifler",
            "Hamur işi Tarifleri",
            "Diyet Tarifler",
            "Tatlı Tarifleri",
            "Makarna ve Pilav Tarifleri",
            "Vegan Tarifler",
            "Çorba Tarifleri ",
            "Bakliyat Yemekleri",
            "Et Yemekleri",
            "Sebze Yemekleri",
            "Glutensiz Tarifler",
            "Fast-Food Tarifler",
            "Çocuklar için Tarifler",
            "İçecek ve Kokteyl Tarifleri",
            "Reçel, Turşu ve Salça Tarifleri",
            "Salata, Meze ve Sos Tarifleri",
            "Aperatifler"
        )
        val foodCategoryAdapter = ArrayAdapter(requireContext(), R.layout.list_item, foodCategoryItems)
        (binding.inputFoodCategory.editText as AutoCompleteTextView).setAdapter(foodCategoryAdapter)
    }

}