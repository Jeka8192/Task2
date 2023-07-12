package com.example.myprofilelayouts.utils.ext

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myprofilelayouts.R

fun ImageView.loadImage(image: String) {
    Glide.with(this)
        .load(image)
        .circleCrop()
        .placeholder(R.drawable.ic_user_avatar)
        .error(R.drawable.ic_user_avatar)
        .into(this)
}
