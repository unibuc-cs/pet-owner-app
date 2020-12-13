package ip.team13.petowner.data.repository

import ip.team13.petowner.data.dto.ActivityEntryModel

class ActivitiesRepository {

    fun getActivities(petId: String): List<ActivityEntryModel> = arrayListOf(
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
        ActivityEntryModel.getPlaceholder(),
    )
}