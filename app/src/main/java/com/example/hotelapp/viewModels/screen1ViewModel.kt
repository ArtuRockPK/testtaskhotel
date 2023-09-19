package com.example.hotelapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotelapp.Retrofit.FirstScreenData
import com.example.hotelapp.Retrofit.ThirdScreenData
import com.example.hotelapp.Retrofit.secondScreenData
import com.example.hotelapp.repository.Repository

class screen1ViewModel:ViewModel() {
    val data1StScreen: LiveData<FirstScreenData> = Repository.getScreen1data()
    val data2StScreen: LiveData<secondScreenData> = Repository.getScreen2data()
    val data3StScreen: LiveData<ThirdScreenData> = Repository.getScreen3data()
    var phoneNumber: MutableLiveData<String> = MutableLiveData("+7 (***) ***-**-**")
    var emailAddress:MutableLiveData<String> = MutableLiveData("")
}
