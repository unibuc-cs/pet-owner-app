package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import kotlin.random.Random

enum class CostTrackerCategory(val title: String) {
    FOOD("Food"),
    HEALTH("Health"),
    FUN("Fun");
}

data class CostTrackerModel(
    @field:Json(name = "Category")
    val category: String,
    @field:Json(name = "ItemName")
    val name: String,
    @field:Json(name = "Money")
    val cost: Double
) {
    companion object {
        fun getPlaceholder() = CostTrackerModel(
            category = CostTrackerCategory.values().map { it.title }.random().toString(),
            name = "Item ${Random.nextInt()}",
            cost = Random.nextDouble() * 100
        )
    }
}

data class CostItemPeriod(val start: String, val end: String)

data class CostTrackerRecylerViewModel(
    @field:Json(name = "category")
    val category: String,
    @field:Json(name = "itemName")
    val name: String,
    @field:Json(name = "money")
    val cost: Double
)