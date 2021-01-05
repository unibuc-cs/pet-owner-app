package ip.team13.petowner.data.repository

import ip.team13.petowner.data.dto.PetEntryModel

class PetRepository {

    val imageUrl: String
        get() = "https://picsum.photos/200/200"

    var name: String = "Doggo"

    fun getPets(): List<PetEntryModel> = arrayListOf(
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
    )
}