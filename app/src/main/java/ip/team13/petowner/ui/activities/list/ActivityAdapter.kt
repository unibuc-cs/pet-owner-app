package ip.team13.petowner.ui.activities.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.domain.*
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.databinding.*

class ActivityAdapter(
    var data: ArrayList<ActivityData>,
    private val selectedPet: ObservableField<PetEntryModel>,
    private val preferences: Preferences
) : ListAdapter<ActivityData, RecyclerView.ViewHolder>(activityDataDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {

            ActivityDataType.ACTIVITY_SELECT_PET.ordinal -> {
                val binding = DataBindingUtil.inflate<ItemSelectPetBinding>(
                    layoutInflater,
                    R.layout.item_select_pet,
                    parent,
                    false
                )

                SelectPetViewHolder(binding)
            }

            ActivityDataType.ACTIVITY_PETS.ordinal -> {
                val binding = DataBindingUtil.inflate<ItemListPetsBinding>(
                    layoutInflater,
                    R.layout.item_list_pets,
                    parent,
                    false
                )

                PetsListViewHolder(binding)
            }

            ActivityDataType.ACTIVITY_ADD.ordinal -> {
                val binding = DataBindingUtil.inflate<ItemActivityAddBinding>(
                    layoutInflater,
                    R.layout.item_activity_add,
                    parent,
                    false
                )

                ActivityAddViewHolder(binding)
            }

            ActivityDataType.ACTIVITY_DATE.ordinal -> {
                val binding = DataBindingUtil.inflate<ItemActivityDateBinding>(
                    layoutInflater,
                    R.layout.item_activity_date,
                    parent,
                    false
                )

                DateViewHolder(binding)
            }

            ActivityDataType.ACTIVITY_ITEM.ordinal -> {
                val binding = DataBindingUtil.inflate<ItemActivityBinding>(
                    layoutInflater,
                    R.layout.item_activity,
                    parent,
                    false
                )

                ActivityViewHolder(binding)
            }

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SelectPetViewHolder -> (data[position] as? ActivitySelectPet)?.let {
                holder.bind()
            }
            is PetsListViewHolder -> (data[position] as? ActivityPets)?.let { model ->
                holder.bind(model)
            }
            is ActivityAddViewHolder -> (data[position] as? ActivityAdd)?.let { model ->
                holder.bind(model)
            }
            is DateViewHolder -> (data[position] as? ActivityDate)?.let { model ->
                holder.bind(model)
            }
            is ActivityViewHolder -> (data[position] as? ActivityItem)?.let { model ->
                holder.bind(model)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int =
        data[position].type.ordinal

    private inner class SelectPetViewHolder(
        private val binding: ItemSelectPetBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {}
    }

    private inner class PetsListViewHolder(
        private val binding: ItemListPetsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ActivityPets) {
            binding.viewModel = ItemPetListViewModel()
            binding.adapter = PetsAdapter(model, selectedPet)
            binding.rvPets.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private inner class ActivityAddViewHolder(
        private val binding: ItemActivityAddBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ActivityAdd) {
            binding.viewModel = ItemActivityAddViewModel(model)
        }
    }

    private inner class DateViewHolder(
        private val binding: ItemActivityDateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ActivityDate) {
            binding.model = model
        }
    }

    private inner class ActivityViewHolder(
        private val binding: ItemActivityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(activity: ActivityItem) {
            binding.viewModel = ItemActivityViewModel(activity, preferences)
        }
    }
}

val activityDataDiffCallback = object : DiffUtil.ItemCallback<ActivityData>() {
    override fun areItemsTheSame(oldItem: ActivityData, newItem: ActivityData): Boolean =
        areItemsEqual(oldItem, newItem)

    override fun areContentsTheSame(oldItem: ActivityData, newItem: ActivityData): Boolean =
        areItemsEqual(oldItem, newItem, true)

    private fun areItemsEqual(
        oldItem: ActivityData,
        newItem: ActivityData,
        byContent: Boolean = false
    ): Boolean {
        if (oldItem::class != newItem::class) return false

        return when (oldItem) {
            is ActivitySelectPet -> true
            is ActivityPets -> (newItem as? ActivityPets)?.pets == oldItem.pets
            is ActivityAdd -> true
            is ActivityDate -> (newItem as? ActivityDate)?.date == oldItem.date
            is ActivityItem -> {
                when (byContent) {
                    true ->
                        (newItem as? ActivityItem)?.model == oldItem.model
                    else ->
                        (newItem as? ActivityItem)?.model?.activityId == oldItem.model.activityId
                }
            }
        }
    }
}