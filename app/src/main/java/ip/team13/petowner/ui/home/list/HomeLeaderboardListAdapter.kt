package ip.team13.petowner.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.databinding.HomeLeaderboardListItemBinding
import kotlin.random.Random
import kotlin.random.nextUInt

class HomeLeaderboardListAdapter(
    var items: List<LeaderboardEntry>
) : RecyclerView.Adapter<HomeLeaderboardListItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeLeaderboardListItemViewHolder = HomeLeaderboardListItemViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.home_leaderboard_list_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HomeLeaderboardListItemViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = items.size

    companion object {
        @JvmStatic
        fun create(): HomeLeaderboardListAdapter = HomeLeaderboardListAdapter(emptyList())
    }
}

class HomeLeaderboardListItemViewHolder(private val binding: HomeLeaderboardListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LeaderboardEntry) {
        binding.viewModel = HomeLeaderboardListItemViewModel(
            item,
            when (item.position) {
                0 -> R.drawable.ic_trophy_gold
                1 -> R.drawable.ic_trophy_silver
                2 -> R.drawable.ic_trophy_bronze
                else -> 0 // No drawable for the rest
            }
        )
    }
}

class HomeLeaderboardListItemViewModel(
    private val leaderboardEntry: LeaderboardEntry,
    @DrawableRes val iconRes: Int
) {

    val imageUrl: String
        get() = leaderboardEntry.imageUrl ?: "https://picsum.photos/2${
            Random.nextUInt().rem(100.toUInt())
        }"

    val name: String
        get() = leaderboardEntry.name ?: ""

    val weeklyExp: String
        get() = leaderboardEntry.weeklyExperience.toString()

    fun onClick() {
        leaderboardEntry.onClick()
    }
}

