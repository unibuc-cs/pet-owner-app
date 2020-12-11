package ip.team13.petowner.ui.activities

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.ActivitiesScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivitiesFragment : BaseFragment<ActivitiesScreenBinding>() {

    override val layout: Int
        get() = R.layout.activities_screen

    override val viewModel: ActivitiesViewModel by viewModel()
}