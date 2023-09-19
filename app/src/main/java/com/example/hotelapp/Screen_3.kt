package com.example.hotelapp

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hotelapp.databinding.ActivityScreen3Binding
import com.example.hotelapp.fragments.Fragment_new_tourist
import com.example.hotelapp.modules.isEmailValid
import com.example.hotelapp.modules.phoneNumberMaskChanger

import com.example.hotelapp.modules.roublePrice
import com.example.hotelapp.viewModels.screen1ViewModel


class screen3 : AppCompatActivity() {
    private lateinit var binding: ActivityScreen3Binding
    private lateinit var paymentButton:Button
    private lateinit var viewModel:screen1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[screen1ViewModel::class.java]

        val goneOnStart = listOf<View>(
            binding.relativeLayout4, binding.relativeLayout5,
            binding.relativeLayout6, binding.relativeLayout7,
            binding.relativeLayout8, binding.relativeLayout9
        )
        var phoneNumber = "+7 (***) ***-**-**"
        val errorColor = ContextCompat.getColor(this, R.color.redColor)
        val fieldColor = ContextCompat.getColor(this, R.color.fieldsColor)
//        viewModel.phoneNumber.observe(this, Observer {
//            binding.cellPhoneNumber.setText(viewModel.phoneNumber.value)
//        })
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        runOnUiThread {
            binding.cellPhoneNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val a = s.toString()

                    if(a[0] != '+')viewModel.phoneNumber.value = phoneNumberMaskChanger(s.toString(), viewModel.phoneNumber.value!!)
                    Log.i("123", a)

                    val te:String = viewModel.phoneNumber.value.toString()
                    if (s!!.first().isDigit()) binding.cellPhoneNumber.setText(te)

                }

                override fun afterTextChanged(s: Editable?) {
                    val dddd = s.toString()
                    //binding.cellPhoneNumber.setText(viewModel.phoneNumber.toString())

                }
            })

        }







            binding.emailEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    val email = s.toString().trim()
                    if (!isEmailValid(email)) {
                        binding.emailField.backgroundTintList = ColorStateList.valueOf(errorColor)
                    }
                    else binding.emailField.backgroundTintList = ColorStateList.valueOf(fieldColor)
                }
            })



        val editTextFields = listOf(
            binding.nameEditText, binding.lastNameEditText,
            binding.dateOfBirthEditText, binding.cityzenshipEditText,
            binding.passportDataEditText, binding.docValidTillDateEditText
            )

        goneOnStart.map { it.visibility = View.VISIBLE }
        val arrowImage = Pair(R.drawable.arrow_up, R.drawable.arrow_down)

        val arrowButton = binding.firstTouristButton

        binding.touristButtonInfo.setImageResource(arrowImage.first)
        var counter = -1

        viewModel.data3StScreen.observe(this, Observer {
                val priceString = roublePrice(it.tour_price)
                binding.price.text = priceString
                binding.textView14.text = it.departure
                val formattedMark = "${it.horating} ${it.rating_name}"
                binding.textView3.text = formattedMark
                val formattedData = "${it.tour_date_start}-${it.tour_date_stop}"
                binding.textView17.text = formattedData
                val formattedNights = "${it.number_of_nights} ночей"
                binding.textView18.text = formattedNights
                binding.textView20.text = it.room
                binding.textView21.text = it.nutrition
                binding.tourCost.text = roublePrice(it.tour_price)
                binding.fuelCost.text = roublePrice(it.fuel_charge)
                binding.serviseCost.text = roublePrice(it.service_charge)
                val totalCost = roublePrice(it.tour_price + it.fuel_charge + it.service_charge)
                binding.payment.text = totalCost
                val finalButtonMessage = "Оплатить $totalCost"
                binding.paymentButton.text = finalButtonMessage
        })
        binding.newTouristButton.setOnClickListener {
            counter++
            val numbersOfTourists = listOf<String>("Второй турист","Третий турист","Четвертый турист")
            if (counter in 0..2) {
                val fragment3 = Fragment_new_tourist(numbersOfTourists, counter)
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.nextturist, fragment3)
                    .commit()
            }
        }
        arrowButton.setOnClickListener {
            if (goneOnStart[0].isVisible) {
                goneOnStart.map { it.visibility = View.GONE }
                binding.touristButtonInfo.setImageResource(arrowImage.first)
            } else {
                goneOnStart.map { it.visibility = View.VISIBLE }
                binding.touristButtonInfo.setImageResource(arrowImage.second)
            }
        }
        paymentButton = binding.paymentButton
        paymentButton.setOnClickListener {
            val color = ContextCompat.getColor(this, R.color.redColor)
            if (editTextFields.any { it.text.isEmpty() }) {
               for (i in editTextFields.indices) if (editTextFields[i].text.isEmpty()) goneOnStart[i].backgroundTintList = ColorStateList.valueOf(color)
            } else {
                val startScreen4 = Intent(this,Screen_4::class.java)
                    startActivity(startScreen4)
            }
        }

    }
}