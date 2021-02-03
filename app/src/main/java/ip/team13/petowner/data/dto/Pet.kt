package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import ip.team13.petowner.data.domain.GroupPet
import ip.team13.petowner.data.domain.PetDetails
import kotlin.math.sign
import kotlin.random.Random

data class PetEntryModel(
    @field:Json(name = "petId")
    val id: Int,
    @field:Json(name = "petName")
    val name: String?,
    @field:Json(name = "age")
    val age: Int?,
    @field:Json(name = "weigth")
    val weight: Double?,
    @field:Json(name = "species")
    val species: String?,
    @field:Json(name = "race")
    val race: String,
    @field:Json(name = "petActivities")
    val petActivities: List<ActivityEntry>?,
    @field:Json(name = "groupId")
    val groupId: Int?,
    @field:Json(name = "photo")
    val pictureUrl: String?
) {

    companion object {
        fun getPlaceholder(): PetEntryModel {
            val random = (Random.nextInt() % 20).let { it * sign(it.toDouble()) }
            val pictures = arrayListOf(
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=1200:*",
                "data:image/jpeg;base64,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",
                "https://cdn.akc.org/content/article-body-image/lab_puppy_dog_pictures.jpg",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/why-cats-are-best-pets-1559241235.jpg?crop=1.00xw:0.753xh;0,0.137xh&resize=1200:*",
                "https://i.insider.com/5484d9d1eab8ea3017b17e29?width=600&format=jpeg&auto=webp",

                )
            return PetEntryModel(
                id = random.toInt(),
                name = "Lex $random",
                groupId = random.toInt(),
                pictureUrl = pictures.random(),
                age = random.toInt(),
                petActivities = random.let {
                    ArrayList<ActivityEntry>().apply {
                        for (i in 1..random.toInt()) {
                            add(ActivityEntry.getPlaceholder())
                        }
                    }
                },
                race = "Race $random",
                species = "Species $random",
                weight = random.toDouble()
            )
        }
    }
}

data class PetUpdateModel(
    @field:Json(name = "petName")
    val petName: String?,
    @field:Json(name = "age")
    val age: Int?,
    @field:Json(name = "photo")
    val photo: String?,
    @field:Json(name = "weigth")
    val weight: Double?,
    @field:Json(name = "species")
    val species: String?,
    @field:Json(name = "race")
    val race: String?
)

fun PetEntryModel.toGroupPet() =
    GroupPet(
        petId = id.toInt(),
        name = name,
        photoUrl = pictureUrl
    )

fun PetEntryModel.toPetDetails() =
    PetDetails(
        petId = id.toInt(),
        name = name,
        photoUrl = pictureUrl,
        age = age,
        weight = weight,
        species = species,
        race = race
    )