package com.example.hotelapp.Retrofit
import retrofit2.http.GET


interface getScreenData {
        @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
         suspend fun getScreenData(): FirstScreenData
    }


interface getSecondScreenData {
    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
      suspend fun getSecondScreenData():secondScreenData
}
interface getThirdScreenData {
    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getThirdScreenData():ThirdScreenData
}