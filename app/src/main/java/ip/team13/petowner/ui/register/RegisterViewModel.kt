package ip.team13.petowner.ui.register

import androidx.databinding.ObservableField
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.isValidEmail
import ip.team13.petowner.data.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository) : BaseViewModel() {

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

        CoroutineScope(Dispatchers.IO).launch {
            authRepository.register(
                email = userEmail,
                password = userPassword,
                inviteCode = inviteCode.get() ?: ""
            )
        }
    }
}