package ip.team13.petowner.data.repository

import androidx.lifecycle.MutableLiveData
import ip.team13.petowner.data.dto.ActivityEntryModel

class ActivitiesRepository {

    private val activities: MutableLiveData<ArrayList<ActivityEntryModel>> = MutableLiveData()

    fun getActivities(petId: String): ArrayList<ActivityEntryModel> {

        if (activities.value.isNullOrEmpty()) {
            activities.value = arrayListOf(
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
        return activities.value ?: ArrayList()
    }

    fun addActivity(activityEntryModel: ActivityEntryModel) {
        activities.value?.add(activityEntryModel)
    }
}