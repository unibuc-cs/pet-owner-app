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
        petOwnerAPI.getPetsAndActivities(userId = sharedPreferences.getUserId() ?: "")
    } catch (ex: Exception) {
        ex.message?.logError()
        ArrayList()
    }
}