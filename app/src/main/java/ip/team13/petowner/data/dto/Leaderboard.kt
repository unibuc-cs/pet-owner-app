package ip.team13.petowner.data.dto

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