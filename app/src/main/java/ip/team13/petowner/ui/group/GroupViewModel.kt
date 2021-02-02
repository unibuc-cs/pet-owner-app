package ip.team13.petowner.ui.group

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.repository.GroupRepository
import ip.team13.petowner.data.repository.UserRepository
import ip.team13.petowner.ui.group.list.GroupPetItemViewModel
import ip.team13.petowner.ui.group.list.GroupUserItemViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GroupViewModel(
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    init {
        with(viewModelScope) {
            launch {
                groupRepository.fetchGroup(userRepository.userFlow.value.groupId)
            }

            launch {
                groupRepository.group.collect {
                    notifyChange()
                }
            }
        }
    }

    lateinit var navigateToAddPet: (groupId: Int) -> Unit

    lateinit var shareInviteCode: (inviteCode: String) -> Unit

    lateinit var navigateToPetProfile: (petId: Int) -> Unit

    lateinit var navigateToUserProfile: (userId: Int) -> Unit

    @get:Bindable
    private val groupId: Int
        get() = groupRepository.group.value.groupId

    @get:Bindable
    private val inviteCode: String
        get() = groupRepository.group.value.inviteCode

    @get:Bindable
    val pets: List<GroupPetItemViewModel>
        get() = groupRepository.group.value.pets.map { pet ->
            GroupPetItemViewModel(
                pet = pet,
                petHappinessScore = 75,
                onClickCallback = this::onPetProfileClick
            )
        }

    @get:Bindable
    val users: List<GroupUserItemViewModel>
        get() = groupRepository.group.value.users.map { user ->
            GroupUserItemViewModel(
                user = user,
                onClickCallback = this::onUserProfileClick
            )
        }

    fun onInviteClick() {
        shareInviteCode(inviteCode)
    }

    fun onAddPetClick() {
        navigateToAddPet(groupId)
    }

    private fun onPetProfileClick(petId: Int) {
        navigateToPetProfile(petId)
    }

    private fun onUserProfileClick(userId: Int) {
        navigateToUserProfile(userId)
    }
}