package ip.team13.petowner.data.repository

import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.domain.Group
import ip.team13.petowner.data.domain.emptyGroup
import ip.team13.petowner.data.dto.toGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GroupRepository(private val api: PetOwnerAPI) {

    private val mutableGroup: MutableStateFlow<Group> = MutableStateFlow(emptyGroup())

    val group: StateFlow<Group>
        get() = mutableGroup

    suspend fun fetchGroup(groupId: Int) = api.getGroup(groupId).also { group ->
        mutableGroup.value = group.toGroup()
    }
}