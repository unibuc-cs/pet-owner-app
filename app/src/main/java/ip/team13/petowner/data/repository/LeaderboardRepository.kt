package ip.team13.petowner.data.repository

import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType

class LeaderboardRepository {

    fun getTop(type: LeaderboardType) = when (type) {
        LeaderboardType.FREE -> arrayListOf(
            LeaderboardEntry("John", "", 10),
            LeaderboardEntry("Christina", "", 2),
            LeaderboardEntry("Michael", "", 7),
            LeaderboardEntry("Angelina", "", 8),
            LeaderboardEntry("Matt", "", 15),
            LeaderboardEntry("Joanna", "", 20),
            LeaderboardEntry("Mike", "", 5)
        )
        LeaderboardType.VIP -> arrayListOf(
            LeaderboardEntry("Alexandra", "", 6),
            LeaderboardEntry("Chrys", "", 12),
            LeaderboardEntry("Sabrina", "", 8),
            LeaderboardEntry("Andrew", "", 3),
            LeaderboardEntry("Briana", "", 10),
            LeaderboardEntry("Fred", "", 15),
            LeaderboardEntry("Emily", "", 18)
        )
    }

    fun getTop5Vip(): List<LeaderboardEntry> = getTop(LeaderboardType.VIP)
        .sortedByDescending { it.weeklyExperience }
        .take(5)

    fun getTop5Free(): List<LeaderboardEntry> = getTop(LeaderboardType.FREE)
        .sortedByDescending { it.weeklyExperience }
        .take(5)

}