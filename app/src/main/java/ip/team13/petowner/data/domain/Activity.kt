package ip.team13.petowner.data.domain

import ip.team13.petowner.data.dto.ActivityEntryModel
import ip.team13.petowner.data.dto.PetEntryModel

enum class ActivityDataType {
    ACTIVITY_SELECT_PET,
    ACTIVITY_PETS,
    ACTIVITY_ADD,
    ACTIVITY_DATE,
    ACTIVITY_ITEM;
}

sealed class ActivityData(
    val type: ActivityDataType
)

class ActivitySelectPet : ActivityData(type = ActivityDataType.ACTIVITY_SELECT_PET)

class ActivityPets(
    val pets: List<PetEntryModel>
) : ActivityData(type = ActivityDataType.ACTIVITY_PETS)

class ActivityAdd(
    val onAddActivity: () -> Unit
) : ActivityData(type = ActivityDataType.ACTIVITY_ADD)

class ActivityDate(
    val date: String
) : ActivityData(type = ActivityDataType.ACTIVITY_DATE)

data class ActivityItem(
    val model: ActivityEntryModel
) : ActivityData(
    type = ActivityDataType.ACTIVITY_ITEM
)