package ip.team13.petowner.ui.cost

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.CostTrackerScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CostTrackerFragment : BaseFragment<CostTrackerScreenBinding>() {

    override val layout: Int
        get() = R.layout.cost_tracker_screen

    override val viewModel: CostTrackerViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvAddItem.setOnClickListener {
            findNavController().navigate(R.id.action_costTracker_to_costDetails)
        }
    }
}