package ip.team13.petowner.data.domain

data class GroupPet(
    val petId: Int,
    val name: String?,
    val photoUrl: String?
)

data class PetDetails(
    val petId: Int,
    val name: String?,
    val photoUrl: String?,
    val age: Int?,
    val weight: Double?,
    val species: String?,
    val race: String?
)