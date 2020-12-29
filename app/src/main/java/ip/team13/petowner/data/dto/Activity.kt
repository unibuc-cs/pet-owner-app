package ip.team13.petowner.data.dto

import java.io.Serializable
import kotlin.random.Random

data class ActivityEntry(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val expPoints: Int? = null,
    val dueTime: String? = null,
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