package ip.team13.petowner.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.ActivitiesScreenBinding
import ip.team13.petowner.ui.activities.list.ActivityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ActivitiesFragment : BaseFragment<ActivitiesScreenBinding>() {

    override val layout: Int
        get() = R.layout.activities_screen

    private val onAddActivity = {
        findNavController().navigate(R.id.action_activitiesFragment_to_activityDetailsFragment)
    }

    override val viewModel: ActivitiesViewModel by viewModel { parametersOf(onAddActivity) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.activities_screen,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.adapter = ActivityAdapter(
            data = viewModel.activityData,
            selectedPet = viewModel.selectedPet
        )
    }

}