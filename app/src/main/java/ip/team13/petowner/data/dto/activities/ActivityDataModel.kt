package ip.team13.petowner.data.dto.activities

import kotlin.random.Random

data class ActivityDataModel(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val expPoints: Int? = null,
    val dueTime: String? = null,
    val petId: String? = null
) {
    companion object {
        fun getPlaceholder(): ActivityDataModel {
            val random = Random.nextInt() % 100
            return ActivityDataModel(
                title = "Title $random",
                description = "Lorem ipsum dolor sit amet $random",
                expPoints = random * 3
            )
        }
    }
}