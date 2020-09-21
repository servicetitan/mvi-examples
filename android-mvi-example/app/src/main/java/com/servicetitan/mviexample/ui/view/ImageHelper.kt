package com.servicetitan.mviexample.ui.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.platform.ContextAmbient
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun generateImage(url: String?): MutableState<ImageAsset> =
    mutableStateOf(ImageAsset(1, 1)).also {
        Glide.with(ContextAmbient.current)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    it.value = resource.asImageAsset()
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }