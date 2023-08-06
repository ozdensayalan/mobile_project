package com.example.yemeksiparisi.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.gecisYap(it: View, id:Int){
    Navigation.findNavController(it).navigate(id)
}

fun Navigation.gecisYap(it: View, id: NavDirections){
    Navigation.findNavController(it).navigate(id)
}
