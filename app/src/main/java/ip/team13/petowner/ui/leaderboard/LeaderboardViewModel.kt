package ip.team13.petowner.ui.leaderboard

import androidx.databinding.Bindable
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.repository.LeaderboardRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    private val leaderboardRepository: LeaderboardRepository
) : BaseViewModel() {

    init {
        refreshData()
    }

    @get:Bindable
    var leaderboardType: LeaderboardType = LeaderboardType.BASIC
        set(value) {
            field = value
            refreshData()
        }

    @get:Bindable
    var items: List<LeaderboardEntry> = ArrayList()

    @get:Bindable
    var person1: LeaderboardEntry? = null

    @get:Bindable
    var person2: LeaderboardEntry? = null

    @get:Bindable
    var person3: LeaderboardEntry? = null

    private fun refreshData() {
        viewModelScope.launch {
            leaderboardRepository.getTop(size = 20, type = leaderboardType).let { leaderboard ->
                person1 = leaderboard.getOrNull(0)
                person2 = leaderboard.getOrNull(1)
                person3 = leaderboard.getOrNull(2)
                if (leaderboard.size >= 3){
                    items = leaderboard.subList(3, leaderboard.size)
                }
            }
        }
    }

}