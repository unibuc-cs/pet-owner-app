package ip.team13.petowner.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.register_screen

    override val viewModel: RegisterViewModel by viewModel()
}