package ip.team13.petowner.ui.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.AppConstants.ARG_OBJECT
import ip.team13.petowner.data.domain.ActivityData
import ip.team13.petowner.data.dto.ActivityEntry
import ip.team13.petowner.databinding.ActivitiesScreenBinding
import ip.team13.petowner.ui.activities.list.ActivityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ActivitiesFragment : BaseFragment<ActivitiesScreenBinding>() {

    companion object {
        const val REQUEST_KEY_NEW_ACTIVITY = "REQUEST_KEY_NEW_ACTIVITY"
    }

    override val layout: Int
        get() = R.layout.activities_screen

    private var adapter: ActivityAdapter? = null

    private val onAddActivity = {
        findNavController().navigate(R.id.action_activitiesFragment_to_activityDetailsFragment)
    }

    override val viewModel: ActivitiesViewModel by viewModel {
        parametersOf(onAddActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(REQUEST_KEY_NEW_ACTIVITY) { requestKey, bundle ->
            (bundle.get(ARG_OBJECT) as? ActivityEntry)?.let { activity ->
                viewModel.addActivity(activity)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ActivityAdapter(
            data = ArrayList<ActivityData>().apply { addAll(viewModel.activityData) },
            selectedPet = viewModel.selectedPet
        )
        binding.adapter = adapter
    }
}