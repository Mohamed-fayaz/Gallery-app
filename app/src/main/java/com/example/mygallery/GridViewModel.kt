package com.example.mygallery

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext

class GridViewModel : ViewModel() {
    private val _navigateToSelectedImage = MutableLiveData<ImageModel?>()

    val navigateToImage: MutableLiveData<ImageModel?>
        get() = _navigateToSelectedImage

    fun displayImageDetails(countryProperty: ImageModel) {
        _navigateToSelectedImage.value = countryProperty

    }


    fun displayImageDetailsComplete() {
        _navigateToSelectedImage.value = null
    }
}