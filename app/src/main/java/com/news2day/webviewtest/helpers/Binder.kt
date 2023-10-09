package com.news2day.webviewtest.helpers

import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.news2day.webviewtest.constants.Constants

@BindingAdapter("catImage")
fun loadImage(ivCatImage: ImageView, url: String){
    Glide.with(ivCatImage)
        .load(Constants.baseUrl + url)
        .into(ivCatImage)
}