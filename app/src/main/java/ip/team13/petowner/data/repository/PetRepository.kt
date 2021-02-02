package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.domain.PetDetails
import ip.team13.petowner.data.dto.*

class PetRepository(
    private val sharedPreferences: Preferences,
    private val petOwnerAPI: PetOwnerAPI
) {

    suspend fun getPet(petId: Int): PetDetails? {
        try {
            return petOwnerAPI.getPet(petId).toPetDetails()
        } catch (e: java.lang.Exception) {
            e.message?.logError()
        }
        return null
    }

    suspend fun getPets() = try {
        petOwnerAPI.getGroupPets(userId = sharedPreferences.getUserId())
    } catch (ex: Exception) {
        ex.message?.logError()
        ArrayList()
    }

    suspend fun addPet(addPetModel: AddPetModel) =
        petOwnerAPI.addPet(
            AddPetRequestModel(
                userId = sharedPreferences.getUserId(),
                pet = addPetModel.apply { groupId = sharedPreferences.getUserId() }
            )
        )

    suspend fun updatePet(petId: Int, pet: PetUpdateModel) {
        petOwnerAPI.updatePet(petId, pet)
    }
}