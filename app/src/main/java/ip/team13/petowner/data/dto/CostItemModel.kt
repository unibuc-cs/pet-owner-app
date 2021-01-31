package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import kotlin.random.Random

enum class CostTrackerCategory(val title: String) {
    FOOD("Food"),
    HEALTH("Health"),
    FUN("Fun");
}

data class CostItemModel(
    @field:Json(name = "Category")
    val category: String,
    @field:Json(name = "ItemName")
    val name: String,
    @field:Json(name = "Money")
    val cost: Double
) {
    companion object {
        fun getPlaceholder() = CostItemModel(
            category = CostTrackerCategory.values().map { it.title }.random().toString(),
            name = "Item ${Random.nextInt()}",
            cost = Random.nextDouble() * 100
        )
    }
}