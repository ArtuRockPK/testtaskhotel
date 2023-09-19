package com.example.hotelapp.modules


fun roublePrice(money:Int):String {
    return "${money/1000} ${money%1000} ₽"
}