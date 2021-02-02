package ip.team13.petowner.ui.group.list

import ip.team13.petowner.data.domain.GroupPet
import ip.team13.petowner.data.domain.GroupUser

interface GroupItemViewModel

class GroupPetItemViewModel(
    private val pet: GroupPet,
    val petHappinessScore: Int,
    private val onClickCallback: (petId: Int) -> Unit
) : GroupItemViewModel {

    val name: String?
        get() = pet.name

    val photoUrl: String?
        get() = pet.photoUrl

    fun onClick() {
        onClickCallback(pet.petId)
    }
}

class GroupUserItemViewModel(
    private val user: GroupUser,
    private val onClickCallback: (userId: Int) -> Unit
) : GroupItemViewModel {

    val name: String?
        get() = user.name

    val photoUrl: String?
        get() = user.photoUrl

    val isVip: Boolean
        get() = user.isVip

    fun onClick() {
        onClickCallback(user.userId)
    }
}