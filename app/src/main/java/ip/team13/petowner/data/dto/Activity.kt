package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import java.io.Serializable
import kotlin.random.Random

data class ActivityEntry(
    @field:Json(name = "activityId")
    val activityId: Int? = null,
    @Json(name = "Title")
    val title: String? = null,
    @Json(name = "Description")
    val description: String? = null,
    @Json(name = "ExpPoints")
    val expPoints: Int? = null,
    @field:Json(name = "data")
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
    @field:Json(name = "petid")
    val petId: Int? = null,
    @field:Json(name = "activityid")
    val activityId: Int? = null,
    @field:Json(name = "data")
    val date: String? = null,
    @field:Json(name = "recurring")
    val recurring: Boolean? = null,
    @field:Json(name = "recurringinterval")
    val recurringInterval: Int? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "exppoints")
    val expPoints: Int? = null,
    @field:Json(name = "title")
    val title: String? = null
) {
    companion object {
        fun fromActivity(petId: Int, activity: ActivityEntry) =
            PetActivityRequestModel(
                petId = petId,
                date = activity.dueTime,
                title = activity.title,
                description = activity.description,
                recurring = activity.recurring,
                recurringInterval = activity.recurringInterval,
                expPoints = activity.expPoints
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