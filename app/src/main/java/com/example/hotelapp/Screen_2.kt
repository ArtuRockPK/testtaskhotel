package com.example.hotelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hotelapp.Retrofit.RetrofitBuilder
import com.example.hotelapp.Retrofit.secondScreenData
import com.example.hotelapp.databinding.ActivityScreen2Binding
import com.example.hotelapp.fragments.Callbacks
import com.example.hotelapp.fragments.hotel_room_fragment
import com.example.hotelapp.viewModels.screen1ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class Screen_2 : AppCompatActivity(), Callbacks {
    private lateinit var binding: ActivityScreen2Binding
    private lateinit var viewmodel:screen1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel = ViewModelProvider(this).get(screen1ViewModel::class.java)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.containerRecycler)
        viewmodel.data2StScreen.observe(this, Observer {
                if (currentFragment == null && it.rooms.isNotEmpty()) {
                    val fragment3 = hotel_room_fragment(it)
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.containerRecycler, fragment3)
                        .commit()
                }
            })
        binding.backButton.setOnClickListener{
            val backToMainScreen = Intent(this,Screen_1::class.java)
            startActivity(backToMainScreen)
        }
    }
    override fun roomSelected() {
        val startScreen3 = Intent(this,screen3::class.java)
        startActivity(startScreen3)
    }
}