package ip.team13.petowner.data.dto

import com.squareup.moshi.Json

data class UpdateWeeklyExpRequestModel(
    @field:Json(name="weeklyexp")
    val weeklyExp: Int
)