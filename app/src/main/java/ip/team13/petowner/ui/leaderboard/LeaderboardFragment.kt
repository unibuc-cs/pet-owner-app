package ip.team13.petowner.ui.leaderboard

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.data.domain.LeaderboardType
import ip.team13.petowner.databinding.LeaderboardScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardFragment : BaseFragment<LeaderboardScreenBinding>() {

    override val layout: Int
        get() = R.layout.leaderboard_screen

    override val viewModel: LeaderboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLeaderboardTypeTabListener()
    }

    private fun setupLeaderboardTypeTabListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabType = when (tab?.text) {
                    getString(R.string.leaderboard_free) -> LeaderboardType.FREE
                    getString(R.string.leaderboard_vip) -> LeaderboardType.VIP
                    else -> null
                }

                tabType?.let { viewModel.leaderboardType.set(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })
    }
}