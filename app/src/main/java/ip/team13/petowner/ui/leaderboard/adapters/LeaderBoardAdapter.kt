package ip.team13.petowner.ui.leaderboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.data.dto.register.leaderboard.LeaderBoardDataModel
import ip.team13.petowner.databinding.ItemLeaderboardBinding

class LeaderBoardAdapter(
    val list: ArrayList<LeaderBoardDataModel>
) : RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder {
        val binding = DataBindingUtil.inflate<ItemLeaderboardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_leaderboard,
            parent,
            false
        )

        return LeaderBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class LeaderBoardViewHolder(
        private val binding: ItemLeaderboardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: LeaderBoardDataModel) {
            binding.dataModel = model
        }
    }
}