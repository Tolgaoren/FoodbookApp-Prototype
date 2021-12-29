package com.toren.foodbookapp.ui.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.toren.foodbookapp.adapter.FoodItemAdapter
import com.toren.foodbookapp.databinding.FoodsFragmentBinding
import com.toren.foodbookapp.model.Yemek
import com.toren.foodbookapp.ui.viewmodel.FoodsViewModel

class FoodsFragment : Fragment(), FoodItemAdapter.OnItemClickListener {

    private val viewModel: FoodsViewModel by viewModels()
    private var _binding: FoodsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: FoodsFragmentArgs by navArgs()
    private var foodAdapter = FoodItemAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FoodsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gelenYemekler = args.yemekler.toList()

        binding.apply {
            recylerView.layoutManager = LinearLayoutManager(view.context)
            recylerView.adapter = foodAdapter
            foodAdapter.updateList(gelenYemekler)
        }


    }

    override fun onItemClick(position: Int) {
        val food = args.yemekler[position]
        val action = FoodsFragmentDirections.actionFoodsFragmentToFoodFragment(food)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}