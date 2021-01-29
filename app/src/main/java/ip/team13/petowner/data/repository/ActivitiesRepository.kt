package ip.team13.petowner.data.repository

import androidx.lifecycle.MutableLiveData
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.ActivityEntry

class ActivitiesRepository(
    private val petOwnerAPI: PetOwnerAPI
) {

    private val activities: MutableLiveData<ArrayList<ActivityEntry>> = MutableLiveData()

    fun getActivities(petId: String): ArrayList<ActivityEntry> {

        if (activities.value.isNullOrEmpty()) {
            activities.value = arrayListOf(
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
                ActivityEntry.getPlaceholder(),
            )
        }
        return activities.value ?: ArrayList()
    }

    fun  addActivity(activityEntry: ActivityEntry) {
        //TODO call backend
        activities.value?.add(activityEntry)
    }
}