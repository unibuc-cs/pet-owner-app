package ip.team13.petowner.ui.activities.viewModels

import androidx.databinding.ObservableField
import ip.team13.petowner.data.dto.pet.PetDataModel

class ItemPetViewModel (
    val model: PetDataModel,
    val selectedPet: ObservableField<PetDataModel>
) {

    fun onSelect() {
        selectedPet.set(model)
    }
}