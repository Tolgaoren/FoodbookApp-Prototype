package com.toren.foodbookapp.ui.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.toren.foodbookapp.ui.viewmodel.AddFoodViewModel
import com.toren.foodbookapp.R
import com.toren.foodbookapp.adapter.MaterialItemAdapter
import com.toren.foodbookapp.databinding.AddFoodFragmentBinding
import com.toren.foodbookapp.model.Yemek
import com.toren.foodbookapp.utils.Constants

class AddFoodFragment : Fragment(), MaterialItemAdapter.OnItemClickListener {

    private val viewModel: AddFoodViewModel by viewModels()
    private var _binding: AddFoodFragmentBinding? = null
    private val binding get() = _binding!!
    private val materialListDetayli = ArrayList<String>(arrayListOf())
    private val materialList = ArrayList<String>(arrayListOf())
    private var materialAdapter = MaterialItemAdapter(arrayListOf(), this, true)
    private lateinit var auth: FirebaseAuth
    private lateinit var imageUrl: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddFoodFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        val materialTypeAdapter = ArrayAdapter(requireContext(), R.layout.list_item, Constants.olcuBirimleri)
        (binding.inputMaterialType.editText as AutoCompleteTextView).setAdapter(materialTypeAdapter)

        val foodCategoryAdapter = ArrayAdapter(requireContext(), R.layout.list_item, Constants.yemekKategorileri)
        (binding.inputFoodCategory.editText as AutoCompleteTextView).setAdapter(foodCategoryAdapter)

        binding.apply {

            recylerViewMaterials.layoutManager = LinearLayoutManager(view.context)
            recylerViewMaterials.adapter = materialAdapter

            inputFoodImage.setOnClickListener {
                val galleryIntent = Intent()
                galleryIntent.action = Intent.ACTION_GET_CONTENT
                galleryIntent.type = "image/*"
                startActivityForResult(galleryIntent, 2)
            }

            buttonAddMaterial.setOnClickListener {
                val materialAmount = inputMaterialAmount.editText!!.text.toString()
                val materialType = inputMaterialType.editText!!.text.toString()
                val materialName = inputMaterialName.editText!!.text.toString()

                if (materialControl(materialAmount, materialType, materialName)) {
                    val material = "$materialAmount $materialType $materialName"

                    materialListDetayli.add(material)
                    materialList.add(materialName)
                    materialAdapter.updateList(materialListDetayli)
                    inputMaterialAmount.editText!!.text = null
                    inputMaterialType.editText!!.text = null
                    inputMaterialName.editText!!.text = null
                }

            }

            buttonAddFood.setOnClickListener {

                if (control(
                        inputFoodName.editText!!.text.toString(),
                        materialList,
                        textInputLayout4.editText!!.text.toString(),
                        textInputLayout5.editText!!.text.toString(),
                        inputFoodCategory.editText!!.text.toString(),
                    )
                ) {
                    val yemek = Yemek(
                        yemekIsmi = inputFoodName.editText!!.text.toString(),
                        malzemeler = materialList,
                        malzemelerDetayli = materialListDetayli,
                        hazirlanis = textInputLayout4.editText!!.text.toString(),
                        aciklama = textInputLayout5.editText!!.text.toString(),
                        kategori = inputFoodCategory.editText!!.text.toString(),
                        imgUrl = inputFoodName.editText!!.text.toString() + "?user=" + auth.uid.toString(),
                        user = auth.uid.toString()
                    )
                    viewModel.saveNewFood(yemek, imageUrl)
                    actionToHome()
                    //inputMaterialType.editText!!.setText("adet")
                }

            }
        }

    }

    private fun control(
        yemekIsmi: String,
        malzemeler: ArrayList<String>,
        hazirlanis: String,
        aciklama: String,
        kategori: String
    ): Boolean {
        var control = true

        if (yemekIsmi.isEmpty()) {
            binding.inputFoodName.editText!!.error = "Tarif ismi ekleyiniz"
            control = false
        } else {
            binding.inputFoodName.editText!!.error = null
        }
        if (malzemeler.isEmpty()) {
            Toast.makeText(this.context,"Malzeme giriniz",Toast.LENGTH_SHORT).show()
            control = false
        }
        if (hazirlanis.isEmpty()) {
            binding.textInputLayout4.editText!!.error = "Hazırlanış ekleyiniz"
            control = false
        } else {
            binding.textInputLayout4.editText!!.error = null
        }
        if (aciklama.isEmpty()) {
            binding.textInputLayout5.editText!!.error = "Açıklama ekleyiniz"
            control = false
        } else {
            binding.textInputLayout5.editText!!.error = null
        }
        if (kategori.isEmpty()) {
            binding.inputFoodCategory.error= "Kategori seçiniz"
            control = false
        } else {
            binding.inputFoodCategory.error = null
        }
        /*if (imageUrl.toString().isEmpty()) {
            Toast.makeText(this.context,"Resim giriniz",Toast.LENGTH_SHORT).show()
            control = false
        }*/

        return control
    }

    override fun onItemClick(position: Int) {
        materialAdapter.removeItem(position)
        materialList.removeAt(position)
        materialListDetayli.removeAt(position)
    }

    private fun materialControl(
        materialAmount: String,
        materialType: String,
        materialName: String
    ): Boolean {
        var control = true

        if (materialAmount.isEmpty()) {
            binding.inputMaterialAmount.error = " "
            control = false
        } else {
            binding.inputMaterialAmount.error = null
        }
        if (materialType.isEmpty()) {
            binding.inputMaterialType.error = " "
            control = false
        } else {
            binding.inputMaterialType.error = null
        }
        if (materialName.isEmpty()) {
            binding.inputMaterialName.error = " "
            control = false
        } else {
            binding.inputMaterialName.error = null
        }

        return control
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUrl = data.data!!
            binding.inputFoodImage.setImageURI(imageUrl)
        }
    }

    private fun actionToHome() {
        val action = AddFoodFragmentDirections.actionAddFoodFragmentToHomeFragment2()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}