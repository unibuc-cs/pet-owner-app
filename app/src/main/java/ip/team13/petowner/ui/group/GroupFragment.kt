package ip.team13.petowner.ui.group

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.GroupScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupFragment : BaseFragment<GroupScreenBinding>() {

    override val layout: Int
        get() = R.layout.group_screen

    override val viewModel: GroupViewModel by viewModel()
}