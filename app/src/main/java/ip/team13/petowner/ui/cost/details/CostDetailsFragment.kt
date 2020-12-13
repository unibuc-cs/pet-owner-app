package ip.team13.petowner.ui.cost.details

import androidx.lifecycle.ViewModel
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.CostDetailsScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CostDetailsFragment : BaseFragment<CostDetailsScreenBinding>() {

    override val layout: Int
        get() = R.layout.cost_details_screen

    override val viewModel: ViewModel by viewModel<CostDetailsViewModel>()
}