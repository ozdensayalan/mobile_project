package com.example.yemeksiparisi.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.yemeksiparisi.R
import com.example.yemeksiparisi.databinding.FragmentSepetBinding
import com.example.yemeksiparisi.data.entity.SepetYemekler
import com.example.yemeksiparisi.data.entity.Yemekler
import com.example.yemeksiparisi.ui.adapter.SepetYemeklerAdapter
import com.example.yemeksiparisi.ui.adapter.YemeklerAdapter
import com.example.yemeksiparisi.ui.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var viewModel: SepetViewModel
    private lateinit var binding: FragmentSepetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)

        binding.sepetFragment=this
        binding.sepetToolbarBaslik="Sepetim"

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarSepet)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter=SepetYemeklerAdapter(requireContext(),it,viewModel)
            binding.sepetlerAdapter=adapter
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel
    }

 /*   override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle()
    }
*/
}