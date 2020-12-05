package ip.team13.petowner.ui.activities.details

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityDetailsFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.activity_details_screen

    override val viewModel: ActivityDetailsViewModel by viewModel()
}