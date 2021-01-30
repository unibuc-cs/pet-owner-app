package ip.team13.petowner.ui.activities.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.AppConstants.ARG_OBJECT
import ip.team13.petowner.core.helpers.AppNotificationManager
import ip.team13.petowner.core.helpers.showCustomAlert
import ip.team13.petowner.data.dto.ActivityEntry
import ip.team13.petowner.databinding.ActivityDetailsScreenBinding
import ip.team13.petowner.ui.activities.ActivitiesFragment.Companion.REQUEST_KEY_NEW_ACTIVITY
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ActivityDetailsFragment : BaseFragment<ActivityDetailsScreenBinding>() {

    override val layout: Int
        get() = R.layout.activity_details_screen

    private val onAddActivity: ((ActivityEntry) -> Unit) = { activity ->
        sendNotification(activity)
        context?.showCustomAlert(
            message = "Activity has been added!",
            onPositiveAction = {
                setFragmentResult(REQUEST_KEY_NEW_ACTIVITY, bundleOf(ARG_OBJECT to activity))
                findNavController().popBackStack()
            }
        )
    }

    private val onCancel = {
        findNavController().popBackStack()
    }

    override val viewModel: ActivityDetailsViewModel by viewModel {
        parametersOf(args.petId)
    }

    private val args: ActivityDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onAddActivity = onAddActivity
        viewModel.onCancel = {
            onCancel.invoke()
        }
    }

    private fun sendNotification(activityEntry: ActivityEntry) = context?.apply {
        //TODO add due_time
        AppNotificationManager.buildNotification(
            this,
            "Activity is about to start soon!",
            activityEntry.title ?: "",
            time = System.currentTimeMillis()
        )
    }
}