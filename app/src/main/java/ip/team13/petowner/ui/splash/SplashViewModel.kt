package ip.team13.petowner.ui.splash

import androidx.lifecycle.ViewModel
import ip.team13.petowner.core.persistence.Preferences

class SplashViewModel(private val preferences: Preferences) : ViewModel() {

    lateinit var navigateToLogin: () -> Unit

    lateinit var navigateToHome: () -> Unit

    fun navigateBasedOnAuth() {
        when (preferences.getUserId() != -1 && !preferences.getBearerToken().isNullOrEmpty()) {
            true -> navigateToHome.invoke()
            false -> navigateToLogin.invoke()
        }
    }
}