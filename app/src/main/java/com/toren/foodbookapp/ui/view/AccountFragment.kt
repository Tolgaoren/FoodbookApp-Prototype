package com.toren.foodbookapp.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.toren.foodbookapp.R
import com.toren.foodbookapp.adapter.FoodItemAdapter
import com.toren.foodbookapp.ui.viewmodel.AccountViewModel
import com.toren.foodbookapp.databinding.AccountFragmentBinding
import com.toren.foodbookapp.model.Yemek

class AccountFragment : Fragment(), FoodItemAdapter.OnItemClickListener {

    private val viewModel: AccountViewModel by viewModels()
    private var _binding: AccountFragmentBinding? = null
    private val binding get() = _binding!!
    private var foodAdapter = FoodItemAdapter(arrayListOf(), this)
    private var foodList = ArrayList<Yemek>(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = AccountFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFoodData()
        loadFoodData()

        binding.apply {
            toolbarm.toolbar.inflateMenu(R.menu.account_settings)
            toolbarm.toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.signOut -> {
                        viewModel.signOut()
                        actionToLogin()
                    }
                }
                true
            }

            recylerViewFoods.layoutManager = GridLayoutManager(view.context, 2)
            recylerViewFoods.adapter = foodAdapter
            recylerViewFoods.setHasFixedSize(true)
        }
    }

    override fun onItemClick(position: Int) {
        val food = foodList[position]
        val action = AccountFragmentDirections.actionAccountFragmentToFoodFragment(food)
        findNavController().navigate(action)
    }

    private fun loadFoodData() {
        viewModel.foodList.observe(viewLifecycleOwner, {
            it?.let {
                foodAdapter.updateList(it as ArrayList<Yemek>)
                foodList.clear()
                foodList.addAll(it)
            }
        })
    }

    private fun actionToLogin() {
        val action = AccountFragmentDirections.actionAccountFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}