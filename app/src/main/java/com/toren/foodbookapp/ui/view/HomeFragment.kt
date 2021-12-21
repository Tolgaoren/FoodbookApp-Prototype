package com.toren.foodbookapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.toren.foodbookapp.R
import com.toren.foodbookapp.ui.viewmodel.HomeViewModel
import com.toren.foodbookapp.databinding.HomeFragmentBinding
import com.toren.foodbookapp.ui.viewmodel.HomeRecyclerAdapter

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var db: FirebaseFirestore
    var tarifBaslikFB: ArrayList<String> = ArrayList()
    var tarifFotoFB: ArrayList<String> = ArrayList()
    var tarifAciklamaFB: ArrayList<String> = ArrayList()
    var adapter: HomeRecyclerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        getDataFromFirestore()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = HomeRecyclerAdapter(tarifBaslikFB, tarifFotoFB, tarifAciklamaFB)
        recyclerView.adapter = adapter

        val currentUser = auth.currentUser

        Log.v("FB", currentUser!!.email.toString())
        Log.v("FB", currentUser.uid)

    }
    fun getDataFromFirestore() {
        db.collection("posts").orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Toast.makeText(context,exception.localizedMessage.toString(), Toast.LENGTH_LONG).show() }
                else {
                    if (snapshot != null) {
                        if (!snapshot.isEmpty) {
                            tarifBaslikFB.clear()
                            tarifFotoFB.clear()
                            tarifAciklamaFB.clear()

                            val documents = snapshot.documents
                            for (document in documents) {
                                val userEmail = document.get("userEmail") as String
                                val baslik = document.get("baslik") as String
                                val aciklama = document.get("aciklama") as String
                                val icindekiler = document.get("icindekiler") as String
                                val hazirlanis = document.get("hazirlanis") as String
                                val downloadUrl = document.get("downloadUrl") as String
                                val timestamp =
                                    document.get("date") as com.google.firebase.Timestamp
                                val date = timestamp.toDate()

                                tarifBaslikFB.add(baslik)
                                tarifFotoFB.add(downloadUrl)
                                tarifAciklamaFB.add(aciklama)

                                adapter!!.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
    }

}