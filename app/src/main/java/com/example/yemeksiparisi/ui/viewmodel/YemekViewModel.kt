package com.example.yemeksiparisi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparisi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel() {

    fun kaydet(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: String,yemek_siparis_adet:String) {
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.kaydet(yemek_adi, yemek_resim_adi, yemek_fiyat,yemek_siparis_adet)
        }
    }
}