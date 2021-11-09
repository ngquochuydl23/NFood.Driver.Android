package com.nsolution.nfooddriver.Utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nsolution.nfooddriver.R

class Image(val context : Context?) {

  private var errorImage : Int? = R.color.white

  fun setErrorImage(errorImage: Int?){
    this.errorImage = errorImage
  }

  fun setImage(imageView: ImageView, urlString: String?) {
    if (!urlString.isNullOrEmpty() && context != null) {
      Glide.with(context)
        .load(urlString)
        .error(errorImage!!)
        .into(imageView)
    }
  }

  fun setAvatar(imageView: ImageView, urlString: String?) {
    if (!urlString.isNullOrEmpty() && context != null) {
      Glide.with(context)
        .load(urlString)
        .error(R.drawable.illustration_avatar_default)
        .into(imageView)
    }
  }

  fun setGifImage(GifView: ImageView, drawable: Int){
    GifView.setImageResource(drawable)
  }
}