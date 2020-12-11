package ip.team13.petowner.ui.activities.viewModels

import ip.team13.petowner.data.dto.pet.PetDataModel

class ItemPetViewModel (
    val model: PetDataModel,
    private val onSelectPet: (petId: String) -> Unit
) {

    fun onSelect() {
        onSelectPet.invoke(model.id)
    }
}