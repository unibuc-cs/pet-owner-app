package ip.team13.petowner.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.RegisterScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<RegisterScreenBinding>() {

    override val layout: Int
        get() = R.layout.register_screen

    override val viewModel: RegisterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }
}