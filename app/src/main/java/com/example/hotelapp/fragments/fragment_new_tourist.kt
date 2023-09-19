package com.example.hotelapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentNextTourisBinding


class Fragment_new_tourist(val listOfTourist:List<String>,val counter:Int):  Fragment() {
    private var _binding:FragmentNextTourisBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentNextTourisBinding.inflate(inflater,container,false)
        val arrowImage = binding.arrowButton
        val arrowImages = Pair(R.drawable.arrow_up, R.drawable.arrow_down)
        val visibilityChangeComponents = listOf(
            binding.nameField,
            binding.surNameField,
            binding.dateOfBirthField,
            binding.cityzenshipField,
            binding.passportNumField,
            binding.validTillField
        )
        binding.touristNumber.text = listOfTourist[counter]
        binding.fieldsVisibilityButton.setOnClickListener{
            if (visibilityChangeComponents[0].isVisible) {
                arrowImage.setImageResource(arrowImages.first)
                visibilityChangeComponents.map { it.visibility = View.GONE }
            } else{
                arrowImage.setImageResource(arrowImages.second)
                visibilityChangeComponents.map { it.visibility = View.VISIBLE }
            }
        }
        return binding.root

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}