package com.example.mygallery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListofImages (var list : MutableList<ImageModel>) : Parcelable