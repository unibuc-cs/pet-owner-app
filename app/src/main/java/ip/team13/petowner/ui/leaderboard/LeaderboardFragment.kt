package ip.team13.petowner.ui.leaderboard

import android.os.Bundle
import android.view.View
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.data.dto.register.leaderboard.LeaderBoardDataModel
import ip.team13.petowner.databinding.LeaderboardScreenBinding
import ip.team13.petowner.ui.leaderboard.adapters.LeaderBoardAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardFragment : BaseFragment<LeaderboardScreenBinding>() {

    override val layout: Int
        get() = R.layout.leaderboard_screen

    override val viewModel: LeaderboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.adapter = LeaderBoardAdapter(
            arrayListOf(
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
                LeaderBoardDataModel.newInstance(),
            )
        )
    }

}