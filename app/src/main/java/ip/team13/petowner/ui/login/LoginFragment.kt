package ip.team13.petowner.ui.login

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.LoginScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginScreenBinding>() {

    override val layout: Int
        get() = R.layout.login_screen

    override val viewModel: LoginViewModel by viewModel()
}