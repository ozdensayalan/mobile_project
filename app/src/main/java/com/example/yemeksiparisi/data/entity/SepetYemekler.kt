package com.example.yemeksiparisi.data.entity

import java.io.Serializable

data class SepetYemekler (var sepet_yemek_id:Int,
                     var yemek_adi:String,
                     var yemek_resim_adi:String,
                     var yemek_fiyat:String,
                          var yemek_siparis_adet:Int,
                          var kullanici_adi:String,
) : Serializable