package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.PetEntryModel

class PetRepository(
    private val sharedPreferences: Preferences,
    private val petOwnerAPI: PetOwnerAPI
) {

//    fun getPets(): List<PetEntryModel> = arrayListOf(
//        PetEntryModel.getPlaceholder(),
//        PetEntryModel.getPlaceholder(),
//        PetEntryModel.getPlaceholder(),
//        PetEntryModel.getPlaceholder(),
//        PetEntryModel.getPlaceholder(),
//        PetEntryModel.getPlaceholder(),
//    )

    suspend fun getPets() = try {
//        petOwnerAPI.getPetsAndActivities(userId = sharedPreferences.getUserId() ?: "")
        arrayListOf(
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
        PetEntryModel.getPlaceholder(),
    )
    } catch (ex: Exception) {
        ex.message?.logError()
        ArrayList()
    }
}