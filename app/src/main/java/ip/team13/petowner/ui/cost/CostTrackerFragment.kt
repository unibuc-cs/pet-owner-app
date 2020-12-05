package ip.team13.petowner.ui.cost

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CostTrackerFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.cost_tracker_screen

    override val viewModel: CostTrackerViewModel by viewModel()
}