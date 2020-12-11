package ip.team13.petowner.ui.activities.models

import ip.team13.petowner.data.dto.activities.ActivityDataModel
import ip.team13.petowner.data.dto.pet.PetDataModel

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
    val pets: ArrayList<PetDataModel>
) : ActivityData(type = ActivityDataType.ACTIVITY_PETS)

class ActivityAdd(
    val onAddActivity: () -> Unit
) : ActivityData(type = ActivityDataType.ACTIVITY_ADD)

class ActivityDate(
    val date: String
) : ActivityData(type = ActivityDataType.ACTIVITY_DATE)

data class ActivityItem(
    val model: ActivityDataModel
) : ActivityData(
    type = ActivityDataType.ACTIVITY_ITEM
)