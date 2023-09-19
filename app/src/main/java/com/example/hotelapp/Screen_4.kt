package com.example.hotelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotelapp.databinding.ActivityScreen4Binding

class Screen_4 : AppCompatActivity() {
    lateinit var binding:ActivityScreen4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        val randOrderNumber = (100000..999999).random()
        val finalMessage = "Подтверждение заказа №$randOrderNumber может занять некоторое время (от 1 часа до суток). Как только мы получим ответ от туроператора, вам на почту придет уведомление."
        binding.rand.text = finalMessage
    }
}