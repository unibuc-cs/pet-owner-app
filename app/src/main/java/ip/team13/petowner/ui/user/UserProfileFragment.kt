package ip.team13.petowner.ui.user

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.closeOnDone
import ip.team13.petowner.databinding.UserProfileScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserProfileFragment : BaseFragment<UserProfileScreenBinding>() {

    override val layout: Int
        get() = R.layout.user_profile_screen

    override val viewModel: UserProfileViewModel by viewModel { parametersOf(args.isOwnUserProfile) }

    private val args: UserProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        viewModel.navigateBack = {
            findNavController().popBackStack()
        }
        viewModel.navigateToLogin = {
            findNavController().navigate(R.id.action_userProfileFragment_to_loginFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileName.closeOnDone()
    }
}