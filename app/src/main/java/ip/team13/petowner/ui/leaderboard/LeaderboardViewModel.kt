package ip.team13.petowner.ui.leaderboard

import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.repository.LeaderboardRepository

class LeaderboardViewModel(
    private val leaderboardRepository: LeaderboardRepository
) : BaseViewModel() {

    val leaderboardType = ObservableField(LeaderboardType.FREE)

    val person1 = ObservableField<LeaderboardEntry>()
    val person2 = ObservableField<LeaderboardEntry>()
    val person3 = ObservableField<LeaderboardEntry>()

    @get:Bindable
    val items: List<LeaderboardEntry>
        get() {
            val list = leaderboardRepository.getTop(leaderboardType.get() ?: LeaderboardType.FREE)
            person1.set(list.removeFirst())
            person2.set(list.removeFirst())
            person3.set(list.removeFirst())
            return list
        }
}