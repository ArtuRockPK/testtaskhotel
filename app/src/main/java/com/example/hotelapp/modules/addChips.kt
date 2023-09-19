package com.example.hotelapp.modules

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.hotelapp.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun addChip(context:Context,list: List<String>,chipGroup: ChipGroup) {
    for (i in list) {
        val chip = Chip(context, null, R.drawable.rounded_corners)
        chip.text = i
        chip.setChipBackgroundColorResource(R.color.chipBackroundColor)
        chip.setTextColor(ContextCompat.getColor(context, R.color.chiptextcolor))
        chipGroup.addView(chip)
    }
}