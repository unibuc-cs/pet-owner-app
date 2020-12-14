package ip.team13.petowner.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ip.team13.petowner.core.helpers.isValidEmail
import ip.team13.petowner.data.repository.AuthRepository

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()

    fun login() {
        val userEmail = email.get() ?: return
        val userPassword = password.get() ?: return

        if (!userEmail.isValidEmail()) return

        authRepository.login(userEmail, userPassword)
    }
}