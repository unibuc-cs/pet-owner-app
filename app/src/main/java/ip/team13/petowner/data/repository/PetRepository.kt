package ip.team13.petowner.data.repository

import ip.team13.petowner.data.dto.PetEntryModel

class PetRepository {

    fun getPets(): List<PetEntryModel> = arrayListOf(
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
    )
}