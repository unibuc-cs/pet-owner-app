package ip.team13.petowner.ui.home

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.HomeScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeScreenBinding>() {

    override val layout: Int
        get() = R.layout.home_screen

    override val viewModel: HomeViewModel by viewModel()
}