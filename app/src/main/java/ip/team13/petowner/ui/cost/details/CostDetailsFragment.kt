package ip.team13.petowner.ui.cost.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.showAlertDialog
import ip.team13.petowner.databinding.CostDetailsScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CostDetailsFragment : BaseFragment<CostDetailsScreenBinding>() {

    override val layout: Int
        get() = R.layout.cost_details_screen

    override val viewModel: CostDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showAlert = { message ->
           showAlertMessage(message)
        }
        viewModel.navigateBack = { findNavController().popBackStack() }
    }

    private fun showAlertMessage(message: String) {
        context?.showAlertDialog(
            title = "",
            message = message,
            positiveButtonText = "Ok",
        )
    }
}