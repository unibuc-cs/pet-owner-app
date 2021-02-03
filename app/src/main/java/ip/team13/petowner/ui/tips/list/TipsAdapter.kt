package ip.team13.petowner.ui.tips.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.data.domain.Tip
import ip.team13.petowner.databinding.ItemTipBinding

class TipsAdapter(
    val tips: ArrayList<Tip>
) : RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = DataBindingUtil.inflate<ItemTipBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_tip,
            parent,
            false
        )

        return TipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(tips[position])
    }

    override fun getItemCount(): Int = tips.size

    inner class TipViewHolder(
        private val binding: ItemTipBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Tip) {
            binding.viewModel = ItemTipViewModel(model)
        }
    }
}