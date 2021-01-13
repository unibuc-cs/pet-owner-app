package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.AddPetModel
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.data.dto.PetModel

class PetRepository(
    private val petOwnerAPI: PetOwnerAPI,
) {

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

    suspend fun addPet(addPetModel: AddPetModel): PetModel? {
        return try {
            petOwnerAPI.addPet(addPetModel)
        } catch (e: Exception) {
            e.message?.logError()
            null
        }
    }
}