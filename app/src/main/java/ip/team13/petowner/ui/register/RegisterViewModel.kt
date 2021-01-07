package ip.team13.petowner.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.isValidEmail
import ip.team13.petowner.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val confirmPassword = ObservableField<String>()
    val inviteCode = ObservableField<String>()
    var navigateToHome: (() -> Unit)? = null

    fun register() {
        val userEmail = email.get() ?: return
        val userPassword = password.get() ?: return
        val userConfirmPassword = confirmPassword.get() ?: return

        if (userPassword != userConfirmPassword) return
        if (!userEmail.isValidEmail()) return

        viewModelScope.launch {
            authRepository.register(
                email = userEmail,
                password = userPassword,
                inviteCode = inviteCode.get(),
                onSuccess = navigateToHome
            )
        }
    }
}