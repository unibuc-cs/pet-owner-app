package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.dto.LeaderboardRequestModel
import ip.team13.petowner.data.dto.toLeaderboardEntry

class LeaderboardRepository(
    private val petOwnerAPI: PetOwnerAPI
) {

//    fun getTop(type: LeaderboardType) = when (type) {
//        LeaderboardType.FREE -> arrayListOf(
//            LeaderboardEntry("John", "", 10),
//            LeaderboardEntry("Christina", "", 2),
//            LeaderboardEntry("Michael", "", 7),
//            LeaderboardEntry("Angelina", "", 8),
//            LeaderboardEntry("Matt", "", 15),
//            LeaderboardEntry("Joanna", "", 20),
//            LeaderboardEntry("Mike", "", 5)
//        )
//        LeaderboardType.VIP -> arrayListOf(
//            LeaderboardEntry("Alexandra", "", 6),
//            LeaderboardEntry("Chrys", "", 12),
//            LeaderboardEntry("Sabrina", "", 8),
//            LeaderboardEntry("Andrew", "", 3),
//            LeaderboardEntry("Briana", "", 10),
//            LeaderboardEntry("Fred", "", 15),
//            LeaderboardEntry("Emily", "", 18)
//        )
//    }

//    fun getTop5Vip(): List<LeaderboardEntry> = getTop(LeaderboardType.VIP)
//        .sortedByDescending { it.weeklyExperience }
//        .take(5)

//    fun getTop5Free(): List<LeaderboardEntry> = getTop(LeaderboardType.FREE)
//        .sortedByDescending { it.weeklyExperience }
//        .take(5)

//    suspend fun getTop5Free(): List<LeaderboardEntry> =
//        petOwnerAPI.getLeaderboards(
//            body = LeaderboardRequestModel(
//                size = 5,
//                isVIP = false
//            )
//        ).map { it.toLeaderboardEntry() }

    suspend fun getTop(size: Int, type: LeaderboardType): List<LeaderboardEntry> {
        return try {
            petOwnerAPI.getLeaderboards(
                body = LeaderboardRequestModel(
                    size = size,
                    isVIP = when (type) {
                        LeaderboardType.VIP -> true
                        else -> false
                    }
                )
            ).map { it.toLeaderboardEntry() }
        } catch (ex: Exception) {
            ex.message?.logError()
            ArrayList()
        }
    }
}