package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import ip.team13.petowner.data.domain.LeaderboardEntry

class LeaderboardEntryModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weeklyExperience: Int
)

fun LeaderboardEntryModel.toLeaderboardEntry() = LeaderboardEntry(
    name, imageUrl, weeklyExperience
)

class LeaderboardRequestModel(
    @Json(name = "size")
    val size: Int,
    @Json(name = "isVIP")
    val isVIP: Boolean
)