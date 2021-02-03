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
import ip.team13.petowner.data.dto.PetActivityRequestModel
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.data.repository.ActivitiesRepository
import ip.team13.petowner.data.repository.PetRepository
import ip.team13.petowner.data.repository.UserRepository
import ip.team13.petowner.ui.activities.list.RepeatType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivitiesViewModel(
    private val onAddActivity: () -> Unit,
    private val petRepository: PetRepository,
    private val userRepository: UserRepository,
    val activityRepository: ActivitiesRepository
) : BaseViewModel() {

    var showAlert: ((String) -> Unit)? = null

    val selectedPet = ObservableField<PetEntryModel>().apply {
        addOnPropertyChanged { newSelectedPet ->
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
            }
        }
    }

    private fun refreshActivityData() {
        val items = ArrayList<ActivityData>()
        if (pets.isEmpty()) return

        items.add(ActivityAdd(onAddActivity))
        items.addAll(activities.map {
            ActivityItem(
                model = it,
                updateTokensAndExp = { updateTokensAndExp(it) }
            )
        })

        activityData = items
        notifyPropertyChanged(BR.activityData)
    }

    fun addActivity(activityEntry: ActivityEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                activityRepository.addPetActivity(PetActivityRequestModel.fromActivity(
                    petId = selectedPet.get()?.id ?: return@launch,
                    activity = activityEntry
                ))

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

    private fun getTokensByRecurring(recurringInterval: Int) = when (recurringInterval) {
        RepeatType.NEVER.value -> RepeatType.NEVER.tokens
        RepeatType.DAILY.value -> RepeatType.DAILY.tokens
        RepeatType.WEEKLY.value -> RepeatType.WEEKLY.tokens
        RepeatType.MONTHLY.value -> RepeatType.MONTHLY.tokens
        else -> 0
    }

    private fun updateTokensAndExp(activity: ActivityEntry) =
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateTokens(getTokensByRecurring(activity.recurringInterval))
            userRepository.updateWeeklyExp(activity.expPoints ?: return@launch)
            activityRepository.deleteActivity(activity.activityId ?: return@launch)

            withContext(Dispatchers.Main) {
                showAlert?.invoke(
                    "Activity completed. You have earned ${activity.expPoints} experience and ${
                        getTokensByRecurring(activity.recurringInterval)
                    } tokens"
                )
            }

            getPetActivities()
        }
}