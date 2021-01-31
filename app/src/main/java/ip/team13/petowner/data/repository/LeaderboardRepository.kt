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

    suspend fun getTop(size: Int, type: LeaderboardType): List<LeaderboardEntry> {
        return try {
            petOwnerAPI.getLeaderboards(
                body = LeaderboardRequestModel(
                    size = size,
                    isvip = when (type) {
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