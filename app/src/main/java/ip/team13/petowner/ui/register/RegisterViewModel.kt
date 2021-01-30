package ip.team13.petowner.ui.register

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.isValidEmail
import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.dto.RegisterForm
import ip.team13.petowner.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val preferences: Preferences,
) : BaseViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val confirmPassword = ObservableField<String>()
    val inviteCode = ObservableField<String>()
    var navigateToHome: (() -> Unit)? = null
    var showAlert: ((String) -> Unit)? = null

    fun register() {
        val userEmail = email.get() ?: return
        val userPassword = password.get() ?: return
        val userConfirmPassword = confirmPassword.get() ?: return

        if (userPassword != userConfirmPassword) return
        if (!userEmail.isValidEmail()) return

        doRegister(userEmail, userPassword)
    }

    fun doRegister(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.register(
                    RegisterForm(
                        email = email,
                        password = password,
                        tokenFCM = preferences.getFCMToken() ?: "",
                        inviteCode = inviteCode.get() ?: ""
                    )
                )
                response.userToken?.let { preferences.saveBearerToken(it) }
                response.userId?.let { preferences.saveUserId(it) }

                if (!response.userToken.isNullOrEmpty() && response.userId != null) {
                    withContext(Dispatchers.Main) {
                        navigateToHome?.invoke()
                    }
                }

            } catch (e: Exception) {
                e.message?.logError()
                withContext(Dispatchers.Main) {
                    showAlert?.invoke("An error has occurred. Please try again later.")
                }
            }
        }
}