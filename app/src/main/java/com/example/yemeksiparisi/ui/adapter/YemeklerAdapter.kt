package com.example.yemeksiparisi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisi.R
import com.example.yemeksiparisi.data.entity.Yemekler
import com.example.yemeksiparisi.databinding.CardTasarimBinding
import com.example.yemeksiparisi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparisi.ui.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisi.utils.gecisYap
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext: Context, var yemeklerListesi:List<Yemekler>,var viewModel:AnasayfaViewModel):RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(var tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding : CardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.card_tasarim,parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim

        t.yemekNesnesi=yemek

        t.imageViewYemek.setImageResource(
            mContext.resources.getIdentifier(yemek.yemek_resim_adi,"drawable",mContext.packageName))

        Glide.with(mContext)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.yemek_resim_adi)
            .placeholder(R.drawable.img) // Yükleme esnasında gösterilecek resim
            .error(R.drawable.img) // Hata durumunda gösterilecek resim
            .into(t.imageViewYemek); // ImageView'a resmi yükle

        t.cardViewYemek.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetay(yemek = yemek)
            Navigation.gecisYap(it,gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}