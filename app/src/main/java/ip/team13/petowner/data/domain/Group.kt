package ip.team13.petowner.data.domain

data class Group(
    val groupId: Int,
    val inviteCode: String,
    val pets: List<GroupPet>,
    val users: List<GroupUser>
)

fun emptyGroup() = Group(-1, "", emptyList(), emptyList())