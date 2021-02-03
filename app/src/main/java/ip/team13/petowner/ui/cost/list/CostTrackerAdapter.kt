package ip.team13.petowner.ui.cost.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.data.dto.CostTrackerModel
import ip.team13.petowner.data.dto.CostTrackerRecylerViewModel
import ip.team13.petowner.databinding.ItemCostTrackerBinding

class CostTrackerAdapter(val items: ArrayList<CostTrackerRecylerViewModel>) :
    RecyclerView.Adapter<CostTrackerAdapter.CostTrackerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostTrackerViewHolder {
        val binding = DataBindingUtil.inflate<ItemCostTrackerBinding>(
            LayoutInflater.from(parent.context), R.layout.item_cost_tracker, parent, false
        )

        return CostTrackerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CostTrackerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CostTrackerViewHolder(
        private val binding: ItemCostTrackerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(costTrackerModel: CostTrackerRecylerViewModel) {
            binding.viewModel = ItemCostTrackerViewModel(costTrackerModel)
        }
    }

}