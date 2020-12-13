package ip.team13.petowner.utils

import android.widget.ImageView
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

@BindingAdapter("leaderboardEntries")
fun submitItems(recyclerView: RecyclerView, items: List<LeaderboardEntry>) {
    (recyclerView.adapter as HomeLeaderboardListAdapter).items = items
}

@BindingAdapter("drawableRes")
fun setImageDrawable(imageView: ImageView, @DrawableRes drawableRes: Int) {
    if (drawableRes == 0) return

    imageView.setImageDrawable(
        ContextCompat.getDrawable(imageView.context, drawableRes)
    )
}