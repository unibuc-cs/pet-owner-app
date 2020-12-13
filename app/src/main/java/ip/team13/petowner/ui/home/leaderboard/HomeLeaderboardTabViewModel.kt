package ip.team13.petowner.ui.home.leaderboard

import androidx.databinding.Bindable
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.repository.LeaderboardRepository

class HomeLeaderboardTabViewModel(
    private val leaderboardType: LeaderboardType,
    private val repository: LeaderboardRepository
) : BaseViewModel() {

    @get:Bindable
    val items: List<LeaderboardEntry>
        get() = if (leaderboardType == LeaderboardType.VIP) {
            repository.getTop5Vip()
        } else {
            repository.getTop5Free()
        }

}