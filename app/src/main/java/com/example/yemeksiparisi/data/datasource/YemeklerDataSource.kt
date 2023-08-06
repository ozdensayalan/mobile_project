package com.example.yemeksiparisi.data.datasource

import android.util.Log
import com.example.yemeksiparisi.data.entity.SepetYemekler
import com.example.yemeksiparisi.data.entity.Yemekler
import com.example.yemeksiparisi.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var ydo: YemeklerDao) {

    suspend fun yemekleriYukle() : List<Yemekler> =
        withContext(Dispatchers.IO){
            return@withContext ydo.yemekleriYukle().yemekler
        }

    suspend fun kaydet(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:String,yemek_siparis_adet:String){
        ydo.kaydet(yemek_adi,yemek_resim_adi,yemek_fiyat.toInt(), yemek_siparis_adet.toInt(),"ozden")
    }
    suspend fun sil(sepet_yemek_id:Int,kullanici_adi:String){
       // Log.e("Kişi sil",kullanici_adi.toString())
        ydo.sil(sepet_yemek_id,kullanici_adi)
    }

    suspend fun sepetiYukle() : List<SepetYemekler> =
        withContext(Dispatchers.IO){
         return@withContext ydo.sepetiYukle("ozden").sepet_yemekler
        }
}