package ip.team13.petowner.ui.login

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.login_screen

    override val viewModel: LoginViewModel by viewModel()
}