package ip.team13.petowner.ui.cost

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.CostTrackerScreenBinding
import ip.team13.petowner.ui.cost.list.CostTrackerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CostTrackerFragment : BaseFragment<CostTrackerScreenBinding>() {

    override val layout: Int
        get() = R.layout.cost_tracker_screen

    override val viewModel: CostTrackerViewModel by viewModel()

    private lateinit var adapter: CostTrackerAdapter

    override fun onStart() {
        super.onStart()
        viewModel.getItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CostTrackerAdapter(arrayListOf())
        binding.adapter = adapter

        binding.addExpenseFab.setOnClickListener {
            findNavController().navigate(R.id.costDetailsFragment)
        }
    }
}