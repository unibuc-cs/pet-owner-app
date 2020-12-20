package ip.team13.petowner.ui.activities

import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.domain.*
import ip.team13.petowner.data.dto.ActivityEntryModel
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.data.repository.ActivitiesRepository
import ip.team13.petowner.data.repository.PetRepository

class ActivitiesViewModel(
    private val onAddActivity: () -> Unit,
    private val petRepository: PetRepository,
    private val activityRepository: ActivitiesRepository
) : BaseViewModel() {

    val selectedPet = ObservableField<PetEntryModel>()

    @get:Bindable
    private val activities: List<ActivityEntryModel>
        get() = activityRepository.getActivities(selectedPet.get()?.id ?: "")

    @get: Bindable
    private val pets: List<PetEntryModel>
        get() {
            val pets = petRepository.getPets()
            selectedPet.set(pets.firstOrNull())
            return pets
        }

    @get: Bindable
    val activityData: ArrayList<ActivityData>
        get() {
            val items = ArrayList<ActivityData>()
            if (pets.isEmpty()) return items

            items.addAll(arrayListOf(
                ActivitySelectPet(),
                ActivityPets(pets),
                ActivityAdd(onAddActivity)
            ))
            activities.groupBy { it.dueTime }.forEach { entry ->
                entry.key?.let { dueTime ->
                    items.add(ActivityDate(dueTime))
                    items.addAll(entry.value.map { ActivityItem(it) })
                }
            }

            return items
        }
}