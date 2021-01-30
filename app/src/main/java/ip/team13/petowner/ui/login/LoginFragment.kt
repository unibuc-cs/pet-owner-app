package ip.team13.petowner.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.SignInButton
import ip.team13.petowner.R
import ip.team13.petowner.core.helpers.showAlertDialog
import ip.team13.petowner.databinding.LoginScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : GoogleLoginFragment<LoginScreenBinding>() {

    override val layout: Int
        get() = R.layout.login_screen

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<SignInButton>(R.id.sign_in_button)?.setOnClickListener {
            signInWithGoogleAccount()
        }

        binding.tvDontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        viewModel.navigateToHome = {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        viewModel.showAlert = { message ->
            context?.showAlertDialog(
                title = "",
                message = message,
                positiveButtonText = "Ok"
            )
        }
    }

    override fun loginWithGoogleAccount(googleId: String, email: String, username: String) {
        viewModel.doGoogleAuth(
            email = email,
            password = username,
        )
    }
}