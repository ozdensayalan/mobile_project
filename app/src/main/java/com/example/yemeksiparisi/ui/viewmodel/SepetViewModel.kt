package com.example.yemeksiparisi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.yemeksiparisi.data.entity.SepetYemekler
import com.example.yemeksiparisi.data.entity.Yemekler
import com.example.yemeksiparisi.data.repo.YemeklerRepository
import com.example.yemeksiparisi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparisi.utils.gecisYap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel() {

    var yemeklerListesi = MutableLiveData<List<SepetYemekler>>()

    init{
        sepetiYukle()
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String) {
        CoroutineScope(Dispatchers.Main).launch {
            val yemeklerListesii=yrepo.sepetiYukle()


            yrepo.sil(sepet_yemek_id,kullanici_adi)

            val yemekId = sepet_yemek_id

            val filteredYemekler = yemeklerListesii.filter { it.sepet_yemek_id != yemekId && it.yemek_adi == yemeklerListesii.find { it.sepet_yemek_id == yemekId }?.yemek_adi }

            for (yemek in filteredYemekler) {
                yrepo.sil(yemek.sepet_yemek_id,kullanici_adi)
            }
                sepetiYukle()

        }

    }

    fun sepetiYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val yemeklerListesii = yrepo.sepetiYukle()
                Log.e("kişi ara", yemeklerListesii.size.toString())
                if (yemeklerListesii.isEmpty() || yemeklerListesii.size == 0) {
                    yemeklerListesi.value = emptyList()
                } else {
                    val tekilYemekler = mutableMapOf<String, SepetYemekler>()
                    for (yemek in yemeklerListesii) {
                        if (tekilYemekler.containsKey(yemek.yemek_adi)) {
                            val eskiYemek = tekilYemekler[yemek.yemek_adi]!!
                            eskiYemek.yemek_siparis_adet += yemek.yemek_siparis_adet
                        } else {
                            tekilYemekler[yemek.yemek_adi] = yemek
                        }
                    }
                    yemeklerListesi.value = tekilYemekler.values.toList()
                }
            }catch (e: Exception) {
                yemeklerListesi.value = emptyList()
            }
        }
    }

}