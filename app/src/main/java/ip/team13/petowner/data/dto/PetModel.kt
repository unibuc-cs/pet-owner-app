package ip.team13.petowner.data.dto

import com.squareup.moshi.Json

data class PetModel(
    @Json(name = "petId")
    val petId: Int,
    @Json(name = "petName")
    val petName: String,
    @Json(name = "age")
    val age: Int,
    @Json(name = "weigth")
    val weight: Double,
    @Json(name = "photo")
    val photo: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "race")
    val race: String,
    @Json(name = "groupId")
    val groupId: String
)