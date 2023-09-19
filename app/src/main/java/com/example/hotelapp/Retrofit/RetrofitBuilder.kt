package com.example.hotelapp.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    const val BASE_URL = "https://run.mocky.io/v3/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getData1:getScreenData by lazy {
        retrofit.create(getScreenData::class.java)
    }

    val getData2:getSecondScreenData by lazy {
        retrofit.create(getSecondScreenData::class.java)
    }


    val getData3:getThirdScreenData by lazy {
        retrofit.create(getThirdScreenData::class.java)
    }


}