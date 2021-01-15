package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.PetEntryModel

class PetRepository(
    private val sharedPreferences: Preferences,
    private val petOwnerAPI: PetOwnerAPI
) {

    suspend fun getPets() = try {
        petOwnerAPI.getPetsAndActivities(userId = sharedPreferences.getUserId().toString())
    } catch (ex: Exception) {
        ex.message?.logError()
        ArrayList()
    }

    val imageUrl: String
        get() = "https://picsum.photos/200/200"

    var name: String = "Doggo"


    suspend fun getPet(petId: Int): PetEntryModel? {
        try {
            return petOwnerAPI.getPet(petId)
        } catch (e: java.lang.Exception) {
            e.message?.logError()
        }
        return null
    }
}