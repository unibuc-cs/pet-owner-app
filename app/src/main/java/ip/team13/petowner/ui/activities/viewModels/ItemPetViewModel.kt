package ip.team13.petowner.ui.activities.viewModels

import androidx.databinding.ObservableField
import ip.team13.petowner.data.dto.pet.PetDataModel
import ip.team13.petowner.helpers.addOnPropertyChanged

class ItemPetViewModel (
    val model: PetDataModel,
    val selectedPet: ObservableField<PetDataModel>
) {

    val isSelected = ObservableField(false)

    init {
        selectedPet.addOnPropertyChanged {
            val newState = it.get() == model
            if (isSelected.get() != newState) {
                isSelected.set(newState)
            }
        }
    }

    fun onSelect() {
        selectedPet.set(model)
    }
}