package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import ip.team13.petowner.data.domain.Group

data class GroupWrapperModel(
    @field:Json(name = "groupId")
    val groupId: Int,
    @field:Json(name = "inviteCode")
    val inviteCode: String,
    @field:Json(name = "pets")
    val pets: List<PetEntryModel>,
    @field:Json(name = "users")
    val users: List<ShortUserWrapperModel>
)

fun GroupWrapperModel.toGroup() = Group(
    groupId = groupId,
    inviteCode = inviteCode,
    pets = pets.map { it.toGroupPet() },
    users = users.map { it.toGroupUser() }
)