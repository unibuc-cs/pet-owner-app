package ip.team13.petowner.ui.pet

import androidx.navigation.fragment.navArgs
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ip.team13.petowner.databinding.PetProfileScreenBinding
import org.koin.core.parameter.parametersOf

class PetProfileFragment : BaseFragment<PetProfileScreenBinding>() {

    override val layout: Int
        get() = R.layout.pet_profile_screen

    override val viewModel: PetProfileViewModel by viewModel { parametersOf(args.petId) }

    private val args: PetProfileFragmentArgs by navArgs()

}