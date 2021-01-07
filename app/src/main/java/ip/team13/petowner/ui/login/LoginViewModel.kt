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
            authRepository.login(
                email = userEmail,
                password = userPassword,
                onSuccess = navigateToHome
            )
        }
    }
}