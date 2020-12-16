package ip.team13.petowner.ui.leaderboard

import androidx.databinding.Bindable
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.repository.LeaderboardRepository
import androidx.databinding.library.baseAdapters.BR

class LeaderboardViewModel(
    private val leaderboardRepository: LeaderboardRepository
) : BaseViewModel() {

    @get:Bindable
    var leaderboardType: LeaderboardType = LeaderboardType.FREE
        set(value) {
            field = value
            notifyChange()
        }

    @get:Bindable
    val items: List<LeaderboardEntry>
        get() = leaderboardRepository.getTop(leaderboardType)
            .subList(0, 3)

    @get:Bindable
    val person1: LeaderboardEntry
        get() = items[0]

    @get:Bindable
    val person2: LeaderboardEntry
        get() = items[1]

    @get:Bindable
    val person3: LeaderboardEntry
        get() = items[3]

}