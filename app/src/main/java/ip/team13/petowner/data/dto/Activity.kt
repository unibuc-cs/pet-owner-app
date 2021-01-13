package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import java.io.Serializable
import kotlin.random.Random

data class ActivityEntry(
    @Json(name = "activityId")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "expPoints")
    val expPoints: Int? = null,
    @Json(name = "dueTime")
    val dueTime: String? = null,
    @Json(name = "petId")
    val petId: String? = null
) : Serializable {
    companion object {
        fun getPlaceholder(): ActivityEntry {
            val random = Random.nextInt() % 100
            val dueTimes = arrayListOf("25 Apr 2020", "26 Apr 2020", "27 Apr 2020", "28 Apr 2020")
            return ActivityEntry(
                title = "Title $random",
                description = "Lorem ipsum dolor sit amet $random",
                expPoints = random * 3,
                dueTime = dueTimes.random()
            )
        }
    }
}