package ip.team13.petowner.data.dto

import com.squareup.moshi.Json

data class AddPetModel(
    @field:Json(name = "PetName")
    val name: String,
    @field:Json(name = "Age")
    val age: Int,
    @field:Json(name = "Weight")
    val weight: Double,
    @field:Json(name = "Photo")
    val photo: String,
    @field:Json(name = "Species")
    val species: String,
    @field:Json(name = "Race")
    val race: String,
    @field:Json(name = "GroupId")
    var groupId: Int
)

data class AddPetRequestModel(
    @field:Json(name = "userid")
    val userId: Int,
    @field:Json(name = "pet")
    val pet: AddPetModel,
)