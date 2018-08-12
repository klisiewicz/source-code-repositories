package pl.karollisiewicz.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun ImageView.circularImageFromUrl(url: String) {
    if (url.isNotBlank()) {
        Glide.with(context)
            .load(url)
            .apply(bitmapTransform(RoundedCornersTransformation(128, 0)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

fun ImageView.imageFromUrl(url: String) {
    if (url.isNotBlank()) {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}