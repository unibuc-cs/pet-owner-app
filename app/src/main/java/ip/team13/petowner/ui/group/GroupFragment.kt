package ip.team13.petowner.ui.group

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.group_screen

    override val viewModel: GroupViewModel by viewModel()
}