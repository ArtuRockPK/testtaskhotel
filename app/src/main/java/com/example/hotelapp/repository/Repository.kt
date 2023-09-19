package com.example.hotelapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.hotelapp.Retrofit.FirstScreenData
import com.example.hotelapp.Retrofit.RetrofitBuilder
import com.example.hotelapp.Retrofit.ThirdScreenData
import com.example.hotelapp.Retrofit.secondScreenData
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Repository {

    fun getScreen1data(): LiveData<FirstScreenData> {
            return  object : LiveData<FirstScreenData>() {
                override fun onActive() {
                    super.onActive()
                        CoroutineScope(IO).launch {
                            val data1 = RetrofitBuilder.getData1.getScreenData()
                            Log.i("123","Зашли на отправку запроса")
                            withContext(Main) {
                                value = data1
                            }
                        }
                    }
                }
            }

    fun getScreen2data(): LiveData<secondScreenData> {
        return  object : LiveData<secondScreenData>() {
            override fun onActive() {
                super.onActive()
                    CoroutineScope(IO).launch {
                        val data1 = RetrofitBuilder.getData2.getSecondScreenData()
                        Log.i("123","Зашли на 2ую отправку запроса")
                        withContext(Main) {
                            value = data1
                        }
                    }
                }
            }
        }

    fun getScreen3data(): LiveData<ThirdScreenData> {
        return  object : LiveData<ThirdScreenData>() {
            override fun onActive() {
                super.onActive()
                CoroutineScope(IO).launch {
                    val data1 = RetrofitBuilder.getData3.getThirdScreenData()
                    Log.i("123","Зашли на 2ую отправку запроса")
                    withContext(Main) {
                        value = data1
                    }
                }
            }
        }
    }
    }

