package ip.team13.petowner.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigateToUserProfile = {

            val args = bundleOf("isOwnUserProfile" to true)

            val extras = FragmentNavigatorExtras(
                binding.homeProfileHeader.homeProfileImage to "userImage",
                binding.homeProfileHeader.tvName to "userName",
                binding.homeProfileHeader.tvLevel to "userLevel",
                binding.homeProfileHeader.tvTokens to "userTokens",
                binding.homeProfileHeader.tvVipTime to "userTime"
            )

            findNavController().navigate(
                R.id.action_homeFragment_to_userProfileFragment,
                args,
                null,
                extras
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.homeLeaderboardLayout.homeLeaderboardPager.apply {
            adapter = HomeLeaderboardAdapter(this@HomeFragment)

            TabLayoutMediator(
                binding.homeLeaderboardLayout.homeLeaderboardTabs,
                this
            ) { tab, position ->
                tab.text = getString(HomeLeaderboardAdapter.getTabTitle(position))
            }.attach()
        }

        binding.homeLeaderboardLayout.homeLeaderboardTitle.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_leaderboardFragment)
        }

    }
}