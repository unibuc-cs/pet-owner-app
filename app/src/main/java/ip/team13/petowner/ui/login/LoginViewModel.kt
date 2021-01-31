package ip.team13.petowner.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.helpers.isValidEmail
import ip.team13.petowner.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    var navigateToHome: (() -> Unit)? = null

    fun login() {
        val userEmail = email.get() ?: return
        val userPassword = password.get() ?: return

        if (!userEmail.isValidEmail()) return

        viewModelScope.launch {
            if (authRepository.login(email = userEmail, password = userPassword))
                navigateToHome?.invoke()
        }
    }

    fun loginWithGoogle(googleId: String, email: String, username: String) {
        viewModelScope.launch {
            if (authRepository.register(email, email.hashCode().toString()) ||
                authRepository.login(email, email.hashCode().toString())
            )
                navigateToHome?.invoke()
        }
    }
}