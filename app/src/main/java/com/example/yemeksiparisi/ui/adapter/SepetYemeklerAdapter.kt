package com.example.yemeksiparisi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisi.R
import com.example.yemeksiparisi.databinding.CardTasarimBinding
import com.example.yemeksiparisi.databinding.CardTasarimSepetBinding
import com.example.yemeksiparisi.data.entity.SepetYemekler
import com.example.yemeksiparisi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparisi.ui.fragment.SepetFragment
import com.example.yemeksiparisi.ui.viewmodel.SepetViewModel
import com.example.yemeksiparisi.utils.gecisYap
import com.google.android.material.snackbar.Snackbar

class SepetYemeklerAdapter(var mContext: Context, var yemeklerListesi:List<SepetYemekler>,var viewModel:SepetViewModel):
    RecyclerView.Adapter<SepetYemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(var tasarim: CardTasarimSepetBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding : CardTasarimSepetBinding= DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.card_tasarim_sepet, parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        t.sepetNesnesi=yemek

        t.imageView2.setImageResource(
            mContext.resources.getIdentifier(yemek.yemek_resim_adi,"drawable",mContext.packageName))

        Glide.with(mContext)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.yemek_resim_adi)
            .placeholder(R.drawable.img) // Yükleme esnasında gösterilecek resim
            .error(R.drawable.img) // Hata durumunda gösterilecek resim
            .into(t.imageView2); // ImageView'a resmi yükle


        t.imageView3.setOnClickListener {
           Snackbar.make(it,"${yemek.yemek_adi} silinsin mi ?",Snackbar.LENGTH_SHORT)
                .setAction("EVET"){
                    sil(yemek.sepet_yemek_id,"ozden")
                }.show()
        }

    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        viewModel.sil(sepet_yemek_id,kullanici_adi)
       // viewModel.sepetiYukle()
       // viewModel.sil(sepet_yemek_id,kulllanici_adi)
    }
}