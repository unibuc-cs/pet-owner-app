package ip.team13.petowner.ui.home.leaderboard

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.databinding.HomeLeaderboardLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeLeaderboardTabFragment(leaderboardType: LeaderboardType) :
    BaseFragment<HomeLeaderboardLayoutBinding>() {

    override val layout: Int
        get() = R.layout.home_leaderboard_tab

    override val viewModel: HomeLeaderboardTabViewModel by viewModel { parametersOf(leaderboardType) }

}