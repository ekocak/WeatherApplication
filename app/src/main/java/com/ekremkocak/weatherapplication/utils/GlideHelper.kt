package com.ekremkocak.weatherapplication.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class GlideHelper {

    companion object {


        fun loadImage(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .centerCrop()
                .into(imageView)
        }

        fun loadImageWithBorder(context: Context, url: String, imageView: ImageView, round: Int) {
//            Glide.with(context)
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(RequestOptions.bitmapTransform(RoundedCorners(round)))
//                .into(imageView)
//
//            val multi = MultiTransformation<Bitmap>(
//                BlurTransformation(25),
//                RoundedCornersTransformation(128, 0,0,128))
//
//            Glide.with(context)
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(RequestOptions.bitmapTransform(multi))
//                .into(imageView)
//
//            GlideApp
//                .with(context)
//                .load(eatFoodyImages.get(2))
//                .transform(
//                    MultiTransformation<Any?>(
//                        BlurTransformation(25, 2),
//                        CropCircleTransformation()
//                    )
//                )
//                .into(imageView3)
        }
    }
}