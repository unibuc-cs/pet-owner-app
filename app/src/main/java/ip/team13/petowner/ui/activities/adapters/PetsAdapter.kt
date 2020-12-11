package ip.team13.petowner.ui.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ip.team13.petowner.R
import ip.team13.petowner.data.dto.pet.PetDataModel
import ip.team13.petowner.databinding.ItemPetBinding
import ip.team13.petowner.ui.activities.models.ActivityPets
import ip.team13.petowner.ui.activities.viewModels.ItemPetViewModel

class PetsAdapter(
    private val data: ActivityPets
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

        fun bind(pet: PetDataModel) {
            binding.viewModel = ItemPetViewModel(
                model = pet,
                onSelectPet = { petId ->
                    data.selectPet(petId)
                }
            )
        }
    }
}