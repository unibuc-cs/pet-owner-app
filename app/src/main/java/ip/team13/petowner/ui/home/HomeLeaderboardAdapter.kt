package ip.team13.petowner.ui.home

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ip.team13.petowner.R
import ip.team13.petowner.ui.splash.SplashFragment

private const val TAB_VIP_POSITION = 0
private const val TAB_FREE_POSITION = 1
private const val TABS_COUNT = 2

class HomeLeaderboardAdapter(fragment: Fragment) :
    FragmentStateAdapter(
        fragment.childFragmentManager,
        fragment.lifecycle
    ) {

    override fun getItemCount(): Int = TABS_COUNT

    override fun createFragment(position: Int): Fragment = when (position) {
        TAB_VIP_POSITION -> SplashFragment()
        else -> throw IllegalStateException("Unexpected leaderboard tab item position: $position")
    }

    companion object {
        @StringRes
        fun getTabTitle(position: Int): Int = when (position) {
            TAB_VIP_POSITION -> R.string.leaderboard_vip
            TAB_FREE_POSITION -> R.string.leaderboard_free
            else -> throw IllegalStateException("Unexpected leaderboard tab position: $position")
        }
    }
}