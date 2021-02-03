package ip.team13.petowner.ui.group.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.databinding.GroupPetCardBinding
import ip.team13.petowner.databinding.GroupUserCardBinding

class GroupAdapter(
    var items: List<GroupItemViewModel>
) : RecyclerView.Adapter<GroupItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.group_pet_card -> {
                val binding = GroupPetCardBinding.inflate(inflater, parent, false)
                GroupPetItemViewHolder(binding)
            }

            R.layout.group_user_card -> {
                val binding = GroupUserCardBinding.inflate(inflater, parent, false)
                GroupUserItemViewHolder(binding)
            }

            else -> throw IllegalStateException("Unexpected Group item view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: GroupItemViewHolder, position: Int) {
        when (holder) {
            is GroupPetItemViewHolder -> (items[position] as? GroupPetItemViewModel)?.let {
                holder.bind(it)
            }

            is GroupUserItemViewHolder -> (items[position] as? GroupUserItemViewModel)?.let {
                holder.bind(it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is GroupPetItemViewModel -> R.layout.group_pet_card
        is GroupUserItemViewModel -> R.layout.group_user_card

        else -> throw IllegalStateException("Unexpected Group item at: $position")
    }

    override fun getItemCount(): Int = items.size

    companion object {
        @JvmStatic
        fun create(): GroupAdapter = GroupAdapter(emptyList())
    }
}

sealed class GroupItemViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

class GroupPetItemViewHolder(private val binding: GroupPetCardBinding) :
    GroupItemViewHolder(binding) {

    fun bind(petItemViewModel: GroupPetItemViewModel) {
        binding.viewModel = petItemViewModel
    }
}

class GroupUserItemViewHolder(private val binding: GroupUserCardBinding) :
    GroupItemViewHolder(binding) {

    fun bind(userItemViewModel: GroupUserItemViewModel) {
        binding.viewModel = userItemViewModel
    }
}