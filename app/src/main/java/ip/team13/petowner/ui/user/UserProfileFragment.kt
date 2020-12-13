package ip.team13.petowner.ui.user

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.UserProfileScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : BaseFragment<UserProfileScreenBinding>() {

    override val layout: Int
        get() = R.layout.user_profile_screen

    override val viewModel: UserProfileViewModel by viewModel()
}