package com.example.hotelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

import com.example.hotelapp.databinding.ActivityMainBinding
import com.example.hotelapp.fragments.PhotoAdapter
import com.example.hotelapp.modules.addChip
import com.example.hotelapp.viewModels.screen1ViewModel
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayoutMediator

class Screen_1 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerTest:ViewPager2
    private lateinit var descriptionTextView:TextView
    private lateinit var viewModel:screen1ViewModel
    private lateinit var chipField:ChipGroup
    private lateinit var screen1Context:Context
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPagerTest = findViewById(R.id.viewPager)
        screen1Context = this
        viewPagerTest.adapter = PhotoAdapter()
        viewModel = ViewModelProvider(this)[screen1ViewModel::class.java]
        TabLayoutMediator(findViewById(R.id.into_tab_layout), viewPagerTest)
        { tab, position -> }.attach()

        binding.chooseRoomButton.setOnClickListener {
            val runNextActivity = Intent(this, Screen_2::class.java)
            startActivity(runNextActivity)
        }
        descriptionTextView = findViewById(R.id.hotel_description)
            chipField = binding.chipGroup
                    viewModel.data1StScreen.observe(this, Observer {
                        descriptionTextView.text = it.about_the_hotel.description
                        binding.mapsLink.text = it.adress
                        val ratingText:String = "${it.rating} ${it.rating_name}"
                        binding.rating.text = ratingText
                        val formattedPrice = "oт ${it.minimal_price / 1000} ${it.minimal_price % 1000} ₽"
                        binding.price.text = formattedPrice
                        viewPagerTest.adapter = PhotoAdapter(listLinks = it.image_urls)
                        TabLayoutMediator(findViewById(R.id.into_tab_layout), viewPagerTest)
                        { tab, position -> }.attach()
                        addChip(
                            context = screen1Context,
                            it.about_the_hotel.peculiarities,
                            chipField
                        )
                    })
            }
}