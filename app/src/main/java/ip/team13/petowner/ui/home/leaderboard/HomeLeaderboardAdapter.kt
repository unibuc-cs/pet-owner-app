package ip.team13.petowner.ui.home.leaderboard

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ip.team13.petowner.R
import ip.team13.petowner.data.domain.LeaderboardType

private const val TAB_VIP_POSITION = 0
private const val TAB_BASIC_POSITION = 1
private const val TABS_COUNT = 2

class HomeLeaderboardAdapter(fragment: Fragment) :
    FragmentStateAdapter(
        fragment.childFragmentManager,
        fragment.lifecycle
    ) {

    override fun getItemCount(): Int = TABS_COUNT

    override fun createFragment(position: Int): Fragment = when (position) {
        TAB_VIP_POSITION -> HomeLeaderboardTabFragment(LeaderboardType.VIP)
        TAB_BASIC_POSITION -> HomeLeaderboardTabFragment(LeaderboardType.BASIC)
        else -> throw IllegalStateException("Unexpected leaderboard tab item position: $position")
    }

    companion object {
        @StringRes
        fun getTabTitle(position: Int): Int = when (position) {
            TAB_VIP_POSITION -> R.string.leaderboard_vip
            TAB_BASIC_POSITION -> R.string.leaderboard_basic
            else -> throw IllegalStateException("Unexpected leaderboard tab position: $position")
        }
    }
}