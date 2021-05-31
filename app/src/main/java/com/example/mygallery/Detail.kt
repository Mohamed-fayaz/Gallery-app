package com.example.mygallery

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mygallery.databinding.FragmentDetailBinding
class Detail : Fragment() {
  lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val image = DetailArgs.fromBundle(requireArguments()).selectedImage
        val imagelist = DetailArgs.fromBundle(requireArguments()).listOfImages


        binding.image = image
        val adapter = GalleryAdapter(GalleryAdapter.OnClickListener{
            binding.image = it
        })

        binding.galleryRvDetail.adapter = adapter

        adapter.submitList(imagelist.list)
        return binding.root

    }
}