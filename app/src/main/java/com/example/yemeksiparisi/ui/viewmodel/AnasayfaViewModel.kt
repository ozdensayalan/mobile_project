package com.example.yemeksiparisi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisi.data.entity.Yemekler
import com.example.yemeksiparisi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yrepo:YemeklerRepository) : ViewModel(){

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init{
        yemekleriYukle()
    }

    fun yemekleriYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value = yrepo.yemekleriYukle()
        }
    }


    fun ara(yemek_adi:String){

        Log.e("kişi ara",yemek_adi.toString())
    }
}