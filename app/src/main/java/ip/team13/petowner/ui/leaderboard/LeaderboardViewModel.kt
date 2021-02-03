package ip.team13.petowner.ui.leaderboard

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
import kotlin.random.Random
import kotlin.random.nextUInt

class LeaderboardViewModel(
    private val leaderboardRepository: LeaderboardRepository
) : BaseViewModel() {

    init {
        refreshData()
    }

    lateinit var navigateToUserProfile: (userId: Int) -> Unit

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
        viewModelScope.launch(Dispatchers.IO) {
            leaderboardRepository.getTop(size = 20, type = leaderboardType).let { leaderboard ->
                withContext(Dispatchers.Main) {
                    person1 = leaderboard.getOrNull(0)?.apply {
                        imageUrl = imageUrl ?: "https://picsum.photos/2${Random.nextUInt().rem(100.toUInt())}"
                        onClick = this@LeaderboardViewModel::onLeaderboardEntryClick
                        notifyPropertyChanged(BR.person1)
                    }
                    person2 = leaderboard.getOrNull(1)?.apply {
                        imageUrl = imageUrl ?: "https://picsum.photos/2${Random.nextUInt().rem(100.toUInt())}"
                        onClick = this@LeaderboardViewModel::onLeaderboardEntryClick
                        notifyPropertyChanged(BR.person2)
                    }
                    person3 = leaderboard.getOrNull(2)?.apply {
                        imageUrl = imageUrl ?: "https://picsum.photos/2${Random.nextUInt().rem(100.toUInt())}"
                        onClick = this@LeaderboardViewModel::onLeaderboardEntryClick
                        notifyPropertyChanged(BR.person3)
                    }
                    if (leaderboard.size >= 3) {
                        items = leaderboard.subList(3, leaderboard.size)
                        notifyPropertyChanged(BR.items)
                    }
                }
            }
        }
    }

    private fun onLeaderboardEntryClick(userId: Int) {
        navigateToUserProfile(userId)
    }
}