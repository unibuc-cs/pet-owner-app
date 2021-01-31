package ip.team13.petowner.data.repository

import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.ActivityEntry
import ip.team13.petowner.data.dto.AttachActivityRequestModel
import ip.team13.petowner.data.dto.PetActivityRequestModel

class ActivitiesRepository(
    private val petOwnerAPI: PetOwnerAPI
) {

    suspend fun addActivity(activityEntry: ActivityEntry) =
        petOwnerAPI.addActivity(activityEntry)

    suspend fun addPetActivity(petActivity: PetActivityRequestModel) =
        petOwnerAPI.addPetActivity(petActivity)

    suspend fun attachActivity(attachActivityRequestModel: AttachActivityRequestModel) =
        petOwnerAPI.attachActivity(attachActivityRequestModel)

    suspend fun deleteActivity(activityId: Int) =
        petOwnerAPI.deleteActivity(activityId)
}