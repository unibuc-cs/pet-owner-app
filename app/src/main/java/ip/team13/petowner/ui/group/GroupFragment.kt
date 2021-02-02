package ip.team13.petowner.ui.group

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.GroupScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupFragment : BaseFragment<GroupScreenBinding>() {

    override val layout: Int
        get() = R.layout.group_screen

    override val viewModel: GroupViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToAddPet = { groupId ->
            findNavController().navigate(
                R.id.action_groupFragment_to_petDetailsFragment,
                bundleOf("groupId" to groupId.toString())
            )
        }

        viewModel.shareInviteCode = this::shareInviteCode

        viewModel.navigateToPetProfile = { petId ->
            findNavController().navigate(
                R.id.action_groupFragment_to_petProfile,
                bundleOf("petId" to petId)
            )
        }

        viewModel.navigateToUserProfile = { userId ->
            findNavController().navigate(
                R.id.action_groupFragment_to_userProfile,
                bundleOf(
                    "isOwnUserProfile" to false,
                    "userId" to userId
                )
            )
        }
    }

    private fun shareInviteCode(inviteCode: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Hey, have some fun with the pets and join the group using this invite code: $inviteCode"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}