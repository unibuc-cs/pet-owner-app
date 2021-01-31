package ip.team13.petowner.ui.activities

import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.addOnPropertyChanged
import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.data.domain.*
import ip.team13.petowner.data.dto.ActivityEntry
import ip.team13.petowner.data.dto.AttachActivityRequestModel
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.data.repository.ActivitiesRepository
import ip.team13.petowner.data.repository.PetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivitiesViewModel(
    private val onAddActivity: () -> Unit,
    private val petRepository: PetRepository,
    val activityRepository: ActivitiesRepository
) : BaseViewModel() {

    var showAlert: ((String) -> Unit)? = null

    val selectedPet = ObservableField<PetEntryModel>().apply {
        addOnPropertyChanged { newSelectedPet ->
//            activities = newSelectedPet.get()?.petActivities ?: ArrayList()
//            refreshActivityData()
            getPetActivities()
        }
    }

    @Bindable
    private var activities: List<ActivityEntry> = ArrayList()

    @Bindable
    private var pets: List<PetEntryModel> = ArrayList()

    @Bindable
    var petsActivityData: ArrayList<ActivityData> = ArrayList()

    @Bindable
    var activityData: ArrayList<ActivityData> = ArrayList()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            with(petRepository.getPets()) {
                pets = this
                selectedPet.set(this.firstOrNull())
                petsActivityData.add(ActivityPets(pets))
                notifyPropertyChanged(BR.petsActivityData)
//                activities = ArrayList<ActivityEntry>().apply {
//                    addAll(selectedPet.get()?.petActivities ?: ArrayList())
//                }
            }
        }
    }

    private fun refreshActivityData() {
//        val items = ArrayList<ActivityData>()
//        if (pets.isEmpty()) return
//
//        items.addAll(
//            arrayListOf(
//                ActivitySelectPet(),
//                ActivityPets(pets),
//                ActivityAdd(onAddActivity)
//            )
//        )
//        activities.groupBy { it.dueTime }.forEach { entry ->
//            entry.key?.let { dueTime ->
//                items.add(ActivityDate(dueTime))
//                items.addAll(entry.value.map { ActivityItem(it) })
//            }
//        }
//
//        activityData = items
//        notifyPropertyChanged(BR.activityData)

        val items = ArrayList<ActivityData>()
        if (pets.isEmpty()) return

        items.add(ActivityAdd(onAddActivity))
        items.addAll(activities.map { ActivityItem(it) })

        activityData = items
        notifyPropertyChanged(BR.activityData)
    }

    fun addActivity(activityEntry: ActivityEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val activityResponse = activityRepository.addActivity(activityEntry)

//                activityRepository.addPetActivity(
//                    PetActivityRequestModel.fromActivity(
//                        petId = selectedPet.get()?.id ?: return@launch,
//                        activityId = activityResponse.activityId,
//                        activity = activityEntry
//                    )
//                )

                activityRepository.attachActivity(
                    AttachActivityRequestModel(
                        petId = selectedPet.get()?.id ?: return@launch,
                        activityId = activityResponse.activityId
                    )
                )

                withContext(Dispatchers.Main) {
                    showAlert?.invoke("Activity added with success!")
                    getPetActivities()
                }

            } catch (e: Exception) {
                e.message?.logError()
                showAlert?.invoke("An error has occurred. Please try again later.")
            }
        }
    }

    private fun getPetActivities() {
        viewModelScope.launch(Dispatchers.IO) {
            val petActivities =
                petRepository.getPetActivities(selectedPet.get()?.id ?: return@launch)
            withContext(Dispatchers.Main) {
                activities = petActivities
                refreshActivityData()
            }
        }
    }
}