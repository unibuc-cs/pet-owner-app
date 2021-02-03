package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import ip.team13.petowner.data.domain.LeaderboardEntry

class LeaderboardEntryModel(
    @Json(name = "userId")
    val id: Int,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "photo")
    val imageUrl: String?,
    @field:Json(name = "weeklyExp")
    val weeklyExperience: Int?
)

fun LeaderboardEntryModel.toLeaderboardEntry(position: Int) = LeaderboardEntry(
    name, imageUrl, weeklyExperience, position
)

class LeaderboardRequestModel(
    @Json(name = "size")
    val size: Int,
    @Json(name = "isvip")
    val isvip: Boolean
)