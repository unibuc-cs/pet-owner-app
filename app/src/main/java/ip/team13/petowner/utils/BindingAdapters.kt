package ip.team13.petowner.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ip.team13.petowner.data.dto.pet.PetDataModel
import ip.team13.petowner.ui.activities.adapters.PetsAdapter


@BindingAdapter("adapter")
fun setRecyclerViewAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {
    recyclerView.adapter = adapter
}

@BindingAdapter("loadImageUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("blackAndWhite")
fun setBlackAndWhite(imageView: ImageView, isBlackAndWhite: Boolean) {
    imageView.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply {
        setSaturation(
            when (isBlackAndWhite) {
                true -> 0F
                else -> 1F
            }
        )
    })
}

@BindingAdapter("animateSelected")
fun setSelectedAnimation(view: View, isSelected: Boolean) {
    val fromValue = when (isSelected) {
        true -> 1f
        else -> 1.15f
    }
    val toValue = when (isSelected) {
        true -> 1.15f
        else -> 1f
    }

    val fadeIn = ScaleAnimation(
        fromValue, toValue, fromValue, toValue,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
    )
    fadeIn.duration = 250
    fadeIn.fillAfter = true
    view.startAnimation(fadeIn)
}