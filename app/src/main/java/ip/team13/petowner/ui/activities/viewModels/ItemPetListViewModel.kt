package ip.team13.petowner.ui.activities.viewModels

import androidx.databinding.ObservableField
import ip.team13.petowner.data.dto.pet.PetDataModel

class ItemPetListViewModel() {
    val selectedPet = ObservableField<PetDataModel>()
}