package com.example.yemeksiparisi.retrofit

import com.example.yemeksiparisi.data.entity.CRUDCevap
import com.example.yemeksiparisi.data.entity.SepetYemeklerCevap
import com.example.yemeksiparisi.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    //http://kasimadalan.pe.hu/kisiler/tum_kisiler.php

    //http://kasimadalan.pe.hu/kisiler/tum_kisiler.php
    //http://kasimadalan.pe.hu/ -> base url
    //kisiler/tum_kisiler.php -> api url

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle() : YemeklerCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun kaydet(@Field("yemek_adi")yemek_adi:String, @Field("yemek_resim_adi")yemek_resim_adi:String,@Field("yemek_fiyat")yemek_fiyat:Int,@Field("yemek_siparis_adet")yemek_siparis_adet:Int,@Field("kullanici_adi")kullanici_adi:String) : CRUDCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepetiYukle(@Field("kullanici_adi")kullanici_adi:String) : SepetYemeklerCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sil(@Field("sepet_yemek_id")sepet_yemek_id:Int,@Field("kullanici_adi")kullanici_adi:String) : CRUDCevap

}