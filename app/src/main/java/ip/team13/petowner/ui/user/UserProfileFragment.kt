package ip.team13.petowner.ui.user

import android.os.Bundle
import android.view.View
import androidx.transition.TransitionInflater
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.closeOnDone
import ip.team13.petowner.databinding.UserProfileScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : BaseFragment<UserProfileScreenBinding>() {

    override val layout: Int
        get() = R.layout.user_profile_screen

    override val viewModel: UserProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileName.closeOnDone()
    }
}