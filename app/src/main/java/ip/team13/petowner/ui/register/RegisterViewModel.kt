package ip.team13.petowner.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ip.team13.petowner.data.repository.AuthRepository
import ip.team13.petowner.helpers.isValidEmail

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val confirmPassword = ObservableField<String>()
    val inviteCode = ObservableField<String>()

    fun register() {
        val userEmail = email.get() ?: return
        val userPassword = password.get() ?: return
        val userConfirmPassword = confirmPassword.get() ?: return

        if (userPassword != userConfirmPassword) return
        if (!userEmail.isValidEmail()) return

        authRepository.register(userEmail, userPassword)
    }
}