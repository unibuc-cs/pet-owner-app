package ip.team13.petowner.ui.activities.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.data.domain.ActivityPets
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.databinding.ItemPetBinding
import ip.team13.petowner.ui.activities.viewModels.ItemPetViewModel

class PetsAdapter(
    private val data: ActivityPets,
    private val selectedPet: ObservableField<PetEntryModel>
) : RecyclerView.Adapter<PetsAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = DataBindingUtil.inflate<ItemPetBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_pet,
            parent,
            false
        )

        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(data.pets[position])
    }

    override fun getItemCount(): Int = data.pets.size

    inner class PetViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pet: PetEntryModel) {
            binding.viewModel = ItemPetViewModel(
                model = pet,
                selectedPet = selectedPet
            )
        }
    }
}