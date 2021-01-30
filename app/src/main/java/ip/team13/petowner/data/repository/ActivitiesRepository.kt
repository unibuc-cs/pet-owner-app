package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.ActivityEntry

class ActivitiesRepository(
    private val petOwnerAPI: PetOwnerAPI
) {

    suspend fun addActivity(activityEntry: ActivityEntry) {

        try {
            petOwnerAPI.addActivity(activityEntry)
        } catch (e: Exception) {
            e.message?.logError()
        }

    }
}