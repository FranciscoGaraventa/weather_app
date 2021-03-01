package com.example.weatherapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadUrl(url: String, requestOption: RequestOptions) {
    Glide.with(context)
        .applyDefaultRequestOptions(requestOption)
        .load(url)
        .into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context)
        .inflate(layoutRes, this, attachToRoot)



