package ip.team13.petowner.ui.activities

import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.addOnPropertyChanged
import ip.team13.petowner.data.domain.*
import ip.team13.petowner.data.dto.ActivityEntry
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.data.repository.ActivitiesRepository
import ip.team13.petowner.data.repository.PetRepository
import kotlinx.coroutines.launch

class ActivitiesViewModel(
    private val onAddActivity: () -> Unit,
    private val petRepository: PetRepository,
    val activityRepository: ActivitiesRepository
) : BaseViewModel() {

    val selectedPet = ObservableField<PetEntryModel>().apply {
        addOnPropertyChanged { newSelectedPet ->
            activities = newSelectedPet.get()?.petActivities ?: ArrayList()
            refreshActivityData()
        }
    }


    @Bindable
    private var activities: List<ActivityEntry> = ArrayList()

    @Bindable
    private var pets: List<PetEntryModel> = ArrayList()

    @Bindable
    var activityData: ArrayList<ActivityData> = ArrayList()

    init {
        viewModelScope.launch {
            with(petRepository.getPets()) {
                pets = this
                selectedPet.set(this.firstOrNull())
                activities = ArrayList<ActivityEntry>().apply {
                    addAll(selectedPet.get()?.petActivities ?: ArrayList())
                }
            }
        }
    }

    private fun refreshActivityData() {
        val items = ArrayList<ActivityData>()
        if (pets.isEmpty()) return

        items.addAll(
            arrayListOf(
                ActivitySelectPet(),
                ActivityPets(pets),
                ActivityAdd(onAddActivity)
            )
        )
        activities.groupBy { it.dueTime }.forEach { entry ->
            entry.key?.let { dueTime ->
                items.add(ActivityDate(dueTime))
                items.addAll(entry.value.map { ActivityItem(it) })
            }
        }

        activityData = items
        notifyPropertyChanged(BR.activityData)
    }
}