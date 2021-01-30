package ip.team13.petowner.ui.home.leaderboard

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.repository.LeaderboardRepository
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class HomeLeaderboardTabViewModel(
    private val leaderboardType: LeaderboardType,
    private val repository: LeaderboardRepository
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            items = repository.getTop(size = 5, type = leaderboardType)
        }
    }

    @get:Bindable
    var items: List<LeaderboardEntry> = ArrayList()
}