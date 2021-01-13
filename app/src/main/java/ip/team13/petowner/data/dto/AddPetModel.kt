package ip.team13.petowner.data.dto

import com.squareup.moshi.Json

data class AddPetModel(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "race")
    val race: String,
    @field:Json(name = "species")
    val species: String,
    @field:Json(name = "weight")
    val weight: Double,
    @field:Json(name = "age")
    val age: Int,
    @field:Json(name = "groupId")
    val groupId: String
)