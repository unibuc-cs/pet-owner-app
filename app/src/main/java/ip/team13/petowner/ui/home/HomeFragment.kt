package ip.team13.petowner.ui.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.HomeScreenBinding
import ip.team13.petowner.ui.home.leaderboard.HomeLeaderboardAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeScreenBinding>() {

    override val layout: Int
        get() = R.layout.home_screen

    override val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeLeaderboardLayout.homeLeaderboardPager.apply {
            adapter = HomeLeaderboardAdapter(this@HomeFragment)

            TabLayoutMediator(
                binding.homeLeaderboardLayout.homeLeaderboardTabs,
                this
            ) { tab, position ->
                tab.text = getString(HomeLeaderboardAdapter.getTabTitle(position))
            }.attach()
        }
    }
}