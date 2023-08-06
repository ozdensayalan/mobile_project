package com.example.yemeksiparisi.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yemeksiparisi.R
import com.example.yemeksiparisi.databinding.FragmentYemekBinding
import com.example.yemeksiparisi.ui.viewmodel.YemekViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekFragment : Fragment() {

    private lateinit var binding: FragmentYemekBinding
    private lateinit var viewModel: YemekViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_yemek, container, false)
        binding.yemekFragment=this
        binding.yemekToolbarBaslik="Ürün Detayı"

        val bundle:YemekFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        binding.yemekNesnesi=gelenYemek

      /*  binding.imageView.setImageResource(
            resources.getIdentifier(yemek.yemek_resim_adi,"drawable",requireContext().packageName))
*/
        Glide.with(this)
            .load("http://kasimadalan.pe.hu/yemekler/resimler/"+gelenYemek.yemek_resim_adi)
            .placeholder(R.drawable.img) // Yükleme esnasında gösterilecek resim
            .error(R.drawable.img) // Hata durumunda gösterilecek resim
            .into(binding.imageView); // ImageView'a resmi yükle

     /*   binding.TextViewFiyat.text = "${yemek.yemek_fiyat} tl"

        binding.TextViewYemek.text = "${yemek.yemek_adi}"

        binding.button.setOnClickListener {
            Snackbar.make(it,"${gelenYemek.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()
            val gecis = YemekFragmentDirections.sepeteGit()
            Navigation.findNavController(it).navigate(gecis)
        }*/
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekViewModel by viewModels()
        viewModel = tempViewModel
    }

        fun kaydet(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:String,yemek_siparis_adet:String){
            viewModel.kaydet(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet)
            val gecis = YemekFragmentDirections.sepeteGit()
            Navigation.findNavController(binding.root).navigate(gecis)
        }
}