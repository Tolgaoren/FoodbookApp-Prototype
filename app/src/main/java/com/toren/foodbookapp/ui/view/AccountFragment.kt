package com.toren.foodbookapp.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.toren.foodbookapp.R
import com.toren.foodbookapp.adapter.FoodAdapter
import com.toren.foodbookapp.ui.viewmodel.AccountViewModel
import com.toren.foodbookapp.databinding.AccountFragmentBinding
import com.toren.foodbookapp.model.Yemek

class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModels()
    private lateinit var binding: AccountFragmentBinding
    private var foodAdapter = FoodAdapter(arrayListOf())
    private lateinit var auth: FirebaseAuth

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

        auth = Firebase.auth

        viewModel.getFoodData(auth.currentUser!!.uid)
        loadFoodData()

        binding.apply {
            toolbarm.toolbar.inflateMenu(R.menu.account_settings)
            toolbarm.toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.signOut -> {
                        auth.signOut()
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
        viewModel.foodList.observe(viewLifecycleOwner, Observer {
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