package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.AddPetModel
import ip.team13.petowner.data.dto.AddPetRequestModel

class PetRepository(
    private val sharedPreferences: Preferences,
    private val petOwnerAPI: PetOwnerAPI
) {

    val imageUrl: String
        get() = "https://picsum.photos/200/200"

    var name: String = "Doggo"

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
}