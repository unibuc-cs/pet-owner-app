package ip.team13.petowner.ui.pet.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.showAlertDialog
import ip.team13.petowner.databinding.PetDetailsScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PetDetailsFragment : BaseFragment<PetDetailsScreenBinding>() {

    override val layout: Int
        get() = R.layout.pet_details_screen

    override val viewModel: PetDetailsViewModel by viewModel { parametersOf(args.petId) }

    private val args: PetDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateBack = {
            findNavController().popBackStack()
        }
        viewModel.showAlert = { message ->
            context?.showAlertDialog(
                title = "",
                message = message,
                positiveButtonText = "Ok",
                onPositiveAction = { findNavController().popBackStack() }
            )
        }
    }

}