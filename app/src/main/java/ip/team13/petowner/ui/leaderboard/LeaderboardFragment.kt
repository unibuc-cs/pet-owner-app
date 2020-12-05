package ip.team13.petowner.ui.leaderboard

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.leaderboard_screen

    override val viewModel: LeaderboardViewModel by viewModel()
}