package com.chen.fakevibrato.widget.glide

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.tmall.wireless.vaf.virtualview.Helper.ImageLoader
import com.tmall.wireless.vaf.virtualview.view.image.ImageBase


class ImageTarget : SimpleTarget<Bitmap> {
    var mImageBase: ImageBase? = null
    var mListener: ImageLoader.Listener? = null

    constructor(mImageBase: ImageBase?) : super() {
        this.mImageBase = mImageBase
    }

    constructor(listener: ImageLoader.Listener) : super() {
        this.mImageBase = mImageBase
    }

    override fun onResourceReady(@NonNull resource: Bitmap,
                                 @Nullable transition: Transition<in Bitmap>?) {
        mImageBase?.setBitmap(resource, true)
        mListener?.onImageLoadSuccess(resource)
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        mListener?.onImageLoadFailed()
    }
}