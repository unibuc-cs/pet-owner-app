package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import java.io.Serializable
import kotlin.random.Random

data class ActivityEntry(
    @Json(name = "ActivityId")
    val id: String? = null,
    @Json(name = "Title")
    val title: String? = null,
    @Json(name = "Description")
    val description: String? = null,
    @Json(name = "ExpPoints")
    val expPoints: Int? = null,
    @Json(name = "data")
    val dueTime: String? = null,
    @Json(name = "PetId")
    val petId: Int,
    @Json(name = "Recurring")
    val recurring: Boolean,
    @Json(name = "RecurringInterval")
    val recurringInterval: Int
) : Serializable {
    companion object {
        fun getPlaceholder(): ActivityEntry {
            val random = Random.nextInt() % 100
            val dueTimes = arrayListOf("25 Apr 2020", "26 Apr 2020", "27 Apr 2020", "28 Apr 2020")
            return ActivityEntry(
                title = "Title $random",
                description = "Lorem ipsum dolor sit amet $random",
                expPoints = random * 3,
                dueTime = dueTimes.random(),
                petId = 1,
                recurring = true,
                recurringInterval = 1
            )
        }
    }
}

data class PetActivityRequestModel(
    @field:Json(name = "PetId")
    val petId: Int? = null,
    @field:Json(name = "ActivityId")
    val activityId: Int? = null,
    @field:Json(name = "Data")
    val date: String? = null,
    @field:Json(name = "Recurring")
    val recurring: Boolean? = null,
    @field:Json(name = "RecurringInterval")
    val recurringInterval: Int? = null,
    @field:Json(name = "Description")
    val description: String? = null,
    @field:Json(name = "ExpPoints")
    val expPoints: Int? = null,
    @field:Json(name = "Title")
    val title: String? = null
) {
    companion object {
        fun fromActivity(petId: Int, activityId: Int, activity: ActivityEntry) =
            PetActivityRequestModel(
                petId = petId,
                activityId = activityId,
                date = activity.dueTime,
                title = activity.title,
                description = activity.description,
                recurring = activity.recurring,
                recurringInterval = activity.recurringInterval,
            )
    }
}

data class AttachActivityRequestModel(
    @field:Json(name = "PetId")
    val petId: Int? = null,
    @field:Json(name = "ActivityId")
    val activityId: Int? = null,
)

data class AddActivityResponseModel(
    @field:Json(name = "activityid")
    val activityId: Int
)