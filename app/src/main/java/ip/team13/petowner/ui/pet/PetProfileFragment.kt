package ip.team13.petowner.ui.pet

import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PetProfileFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.pet_profile_screen

    override val viewModel: PetProfileViewModel by viewModel()
}