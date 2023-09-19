package com.example.hotelapp.modules



fun phoneNumberMaskChanger(input:String,text:String):String{
    val number = if (input[0] == '+') input[1] else input[0]
    var final:StringBuffer = StringBuffer(text)
    var a = ""
    var b = false
    for (i in final) {
        if (i == '*' && !b) {
            a+= number
            b = true
        }
        else a+=i
    }
    return a
}