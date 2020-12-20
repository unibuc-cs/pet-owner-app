package ip.team13.petowner.ui.splash

import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    lateinit var navigateToLogin: () -> Unit

    lateinit var navigateToHome: () -> Unit

    fun navigateBasedOnAuth() {
        // TODO: if user is auth'ed -> navigate to home ; else -> navigate to login
        navigateToLogin()
    }
}