package ip.team13.petowner.data.repository

import ip.team13.petowner.data.domain.LeaderboardEntry

class LeaderboardRepository {

    fun getTop5Vip(): List<LeaderboardEntry> = arrayListOf(
        LeaderboardEntry("John", "", 10),
        LeaderboardEntry("Christina", "", 2),
        LeaderboardEntry("Michael", "", 7),
        LeaderboardEntry("Angelina", "", 8),
        LeaderboardEntry("Matt", "", 15),
        LeaderboardEntry("Joanna", "", 20),
        LeaderboardEntry("Mike", "", 5)
    )
        .sortedByDescending { it.weeklyExperience }
        .take(5)

    fun getTop5Free(): List<LeaderboardEntry> = arrayListOf(
        LeaderboardEntry("Alexandra", "", 6),
        LeaderboardEntry("Chrys", "", 12),
        LeaderboardEntry("Sabrina", "", 8),
        LeaderboardEntry("Andrew", "", 3),
        LeaderboardEntry("Briana", "", 10),
        LeaderboardEntry("Fred", "", 15),
        LeaderboardEntry("Emily", "", 18)
    )
        .sortedByDescending { it.weeklyExperience }
        .take(5)

}