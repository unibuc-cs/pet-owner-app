package ip.team13.petowner.core.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ip.team13.petowner.data.domain.ActivityData
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.dto.CostTrackerRecylerViewModel
import ip.team13.petowner.ui.activities.list.ActivityAdapter
import ip.team13.petowner.ui.group.list.GroupAdapter
import ip.team13.petowner.ui.group.list.GroupItemViewModel
import ip.team13.petowner.ui.cost.list.CostTrackerAdapter
import ip.team13.petowner.ui.home.list.HomeLeaderboardListAdapter

// ********* region View *********

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

@BindingAdapter("imageTint")
fun loadImageTint(imageView: ImageView, colorResource: Int) {
    imageView.setColorFilter(
        ContextCompat.getColor(imageView.context, colorResource),
        PorterDuff.Mode.MULTIPLY
    )
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("visibleOrNot")
fun setInvisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible)
        View.VISIBLE
    else
        View.INVISIBLE
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

@BindingAdapter("drawableTintRes")
fun setDrawableTintRes(textView: TextView, color: Int) {
    textView.compoundDrawablesRelative.forEach {
        it?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}

@BindingAdapter("textWatcher")
fun setTextWatcher(editText: EditText, textWatcher: TextWatcher) {
    editText.addTextChangedListener(textWatcher)
}

// ********* end region View *********


// ********* region ImageView *********

@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}

@BindingAdapter(value = ["loadImageUrl", "placeholder"])
fun loadImageUrlWithPlaceholder(
    imageView: ImageView,
    imageUrl: String?,
    @DrawableRes placeholder: Int
) {
    val requestOptions = RequestOptions().placeholder(placeholder)

    Glide.with(imageView.context)
        .applyDefaultRequestOptions(requestOptions)
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("drawableRes")
fun setImageDrawable(imageView: ImageView, @DrawableRes drawableRes: Int) {
    if (drawableRes == 0) return

    imageView.setImageDrawable(
        ContextCompat.getDrawable(imageView.context, drawableRes)
    )
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
// ********* end region ImageView *********


// ********* region RecyclerView *********

@BindingAdapter("adapter")
fun setRecyclerViewAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {
    recyclerView.adapter = adapter
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun submitItems(recyclerView: RecyclerView, items: List<Any>) {
    when (recyclerView.adapter) {
        is HomeLeaderboardListAdapter ->
            (items as? List<LeaderboardEntry>)?.let {
                (recyclerView.adapter as? HomeLeaderboardListAdapter)?.items = it
                recyclerView.adapter?.notifyDataSetChanged()
            }
        is ActivityAdapter ->
            (items as? ArrayList<ActivityData>)?.let {
                (recyclerView.adapter as? ActivityAdapter)?.data?.clear()
                (recyclerView.adapter as? ActivityAdapter)?.data?.addAll(items)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        is GroupAdapter ->
            (items as? List<GroupItemViewModel>)?.let {
                (recyclerView.adapter as? GroupAdapter)?.items = items
                recyclerView.adapter?.notifyDataSetChanged()
            }
        is CostTrackerAdapter ->
            (items as? ArrayList<CostTrackerRecylerViewModel>)?.let {
                (recyclerView.adapter as? CostTrackerAdapter)?.items?.clear()
                (recyclerView.adapter as? CostTrackerAdapter)?.items?.addAll(items)
                recyclerView.adapter?.notifyDataSetChanged()
            }
    }
}
// ********* end region RecyclerView *********