package com.toren.foodbookapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.toren.foodbookapp.adapter.FoodItemAdapter
import com.toren.foodbookapp.databinding.HomeViewPagerFragmentBinding
import com.toren.foodbookapp.model.Yemek
import com.toren.foodbookapp.ui.viewmodel.HomeViewPagerFragmentViewModel

class HomeViewPagerFragment(val kategori: String) : Fragment() {

    private val viewModel: HomeViewPagerFragmentViewModel by viewModels()
    private var _binding: HomeViewPagerFragmentBinding? = null
    private val binding get() = _binding!!
    private val foodAdapter = FoodItemAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeViewPagerFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFoodData(kategori)
        loadFoodData()

        binding.apply {
            recylerView.layoutManager = GridLayoutManager(view.context, 2)
            recylerView.adapter = foodAdapter
            recylerView.setHasFixedSize(true)
        }


    }

    private fun loadFoodData() {
        viewModel.foodList.observe(viewLifecycleOwner, {
            it?.let {
                foodAdapter.updateList(it as ArrayList<Yemek>)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}