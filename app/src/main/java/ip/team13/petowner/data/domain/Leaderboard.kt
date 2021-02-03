package ip.team13.petowner.data.domain

data class LeaderboardEntry(
    val name: String?,
    var imageUrl: String?,
    val weeklyExperience: Int?,
    val position: Int
)

enum class LeaderboardType {
    VIP,
    BASIC
}