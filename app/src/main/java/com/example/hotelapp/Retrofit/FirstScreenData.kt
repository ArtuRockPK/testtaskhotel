package com.example.hotelapp.Retrofit


data class FirstScreenData(
    val about_the_hotel: AboutTheHotel,
    val adress: String,
    val id: Int,
    val image_urls: List<String>,
    val minimal_price: Int,
    val name: String,
    val price_for_it: String,
    val rating: Int,
    val rating_name: String
)

data class AboutTheHotel(
    val description: String,
    val peculiarities: List<String>
)
