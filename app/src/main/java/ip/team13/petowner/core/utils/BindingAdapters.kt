package ip.team13.petowner.core.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.ui.home.list.HomeLeaderboardListAdapter

@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}

@BindingAdapter("loadImageResource")
fun loadImageResource(imageView: ImageView, imageResource: Int) {
    imageView.setImageResource(imageResource)
}

@BindingAdapter("loadTextResource")
fun loadTextResource(textView: TextView, textResource: Int) {
    textView.text = textView.context.getString(textResource)
}

@BindingAdapter("loadBackgroundColorResource")
fun loadBackgroundColorResource(view: View, colorResource: Int) {
    view.setBackgroundColor(ContextCompat.getColor(view.context, colorResource))
}

@BindingAdapter("adapter")
fun setRecyclerViewAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {
    recyclerView.adapter = adapter
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

@BindingAdapter("leaderboardEntries")
fun submitItems(recyclerView: RecyclerView, items: List<LeaderboardEntry>) {
    (recyclerView.adapter as? HomeLeaderboardListAdapter)?.apply {
        this.items = items
        this.notifyDataSetChanged()
    }
}

@BindingAdapter("drawableRes")
fun setImageDrawable(imageView: ImageView, @DrawableRes drawableRes: Int) {
    if (drawableRes == 0) return

    imageView.setImageDrawable(
        ContextCompat.getDrawable(imageView.context, drawableRes)
    )
}

@BindingAdapter("drawableTintRes")
fun setDrawableTintRes(textView: TextView, color: Int) {
    textView.compoundDrawablesRelative.forEach {
        it?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}