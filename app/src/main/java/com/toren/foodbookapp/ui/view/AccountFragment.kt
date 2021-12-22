package com.toren.foodbookapp.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.toren.foodbookapp.R
import com.toren.foodbookapp.adapter.FoodAdapter
import com.toren.foodbookapp.ui.viewmodel.AccountViewModel
import com.toren.foodbookapp.databinding.AccountFragmentBinding
import com.toren.foodbookapp.model.Yemek

class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModels()
    private lateinit var binding: AccountFragmentBinding
    private var foodAdapter = FoodAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = AccountFragmentBinding.inflate(layoutInflater)
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

            recylerViewFoods.layoutManager = LinearLayoutManager(view.context)
            recylerViewFoods.adapter = foodAdapter
        }
    }

    private fun loadFoodData() {
        viewModel.foodList.observe(viewLifecycleOwner, {
            it?.let {
                foodAdapter.updateList(it as ArrayList<Yemek>)
            }
        })
    }

    private fun actionToLogin() {
        val action = AccountFragmentDirections.actionAccountFragmentToLoginFragment()
        findNavController().navigate(action)
    }

}