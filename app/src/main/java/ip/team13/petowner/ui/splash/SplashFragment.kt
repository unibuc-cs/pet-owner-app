package ip.team13.petowner.ui.splash

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.splash_screen

    override val viewModel: SplashViewModel by viewModel()
}