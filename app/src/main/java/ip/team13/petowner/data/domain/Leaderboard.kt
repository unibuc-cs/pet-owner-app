package ip.team13.petowner.data.domain

data class LeaderboardEntry(
    val name: String,
    val imageUrl: String,
    val weeklyExperience: Int
)

enum class LeaderboardType {
    VIP,
    BASIC
}