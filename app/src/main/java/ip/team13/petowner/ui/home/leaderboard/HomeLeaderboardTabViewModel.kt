package ip.team13.petowner.ui.home.leaderboard

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.BR
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.domain.LeaderboardEntry
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.data.repository.LeaderboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class HomeLeaderboardTabViewModel(
    private val leaderboardType: LeaderboardType,
    private val repository: LeaderboardRepository
) : BaseViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val leaderboard = repository.getTop(size = 5, type = leaderboardType)
            withContext(Dispatchers.Main){
                items = leaderboard
                notifyPropertyChanged(BR.items)
            }
        }
    }

    @get:Bindable
    var items: List<LeaderboardEntry> = arrayListOf()
}