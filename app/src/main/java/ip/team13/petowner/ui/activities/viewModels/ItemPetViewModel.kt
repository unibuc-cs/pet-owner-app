package ip.team13.petowner.ui.activities.viewModels

import androidx.databinding.ObservableField
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.helpers.addOnPropertyChanged

class ItemPetViewModel (
    val model: PetEntryModel,
    val selectedPet: ObservableField<PetEntryModel>
) {

    val isSelected = ObservableField(selectedPet.get() == model)

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