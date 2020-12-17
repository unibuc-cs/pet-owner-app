package ip.team13.petowner.ui.activities.details

import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.ActivityDetailsScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ActivityDetailsFragment : BaseFragment<ActivityDetailsScreenBinding>() {

    override val layout: Int
        get() = R.layout.activity_details_screen

    private val onAddActivity = {
        findNavController().popBackStack()
        //TODO Add successful alert after success adding activity
    }

    private val onCancel = {
        findNavController().popBackStack()
    }

    override val viewModel: ActivityDetailsViewModel by viewModel {
        parametersOf(onAddActivity, onCancel)
    }
}