package com.example.yemeksiparisi.data.repo

import com.example.yemeksiparisi.data.datasource.YemeklerDataSource
import com.example.yemeksiparisi.data.entity.SepetYemekler
import com.example.yemeksiparisi.data.entity.Yemekler

class YemeklerRepository(var yds : YemeklerDataSource) {

    suspend fun yemekleriYukle() : List<Yemekler> = yds.yemekleriYukle()

    suspend fun kaydet(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:String,yemek_siparis_adet:String) = yds.kaydet(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet)

    suspend fun sil(sepet_yemek_id:Int,kullanici_adi:String)=yds.sil(sepet_yemek_id, kullanici_adi)

    suspend fun sepetiYukle() : List<SepetYemekler> =yds.sepetiYukle()
}