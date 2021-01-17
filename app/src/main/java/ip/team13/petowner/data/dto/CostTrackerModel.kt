package ip.team13.petowner.data.dto

import com.squareup.moshi.Json

data class CostTrackerModel(
    @Json(name = "name")
    val name: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "amount")
    val amount: Double
)