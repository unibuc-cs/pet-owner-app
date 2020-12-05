package ip.team13.petowner.ui.pet.details

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PetDetailsFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.pet_details_screen

    override val viewModel: PetDetailsViewModel by viewModel()
}