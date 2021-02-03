package ip.team13.petowner.data.domain

data class LeaderboardEntry(
    val userId: Int,
    val name: String?,
    var imageUrl: String?,
    val weeklyExperience: Int?,
    val position: Int,
    var onClick: (userId: Int) -> Unit
) {
    fun onClick() {
        onClick.invoke(userId)
    }
}

enum class LeaderboardType {
    VIP,
    BASIC
}