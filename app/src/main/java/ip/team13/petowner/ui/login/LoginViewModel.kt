package ip.team13.petowner.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.helpers.ErrorCodes
import ip.team13.petowner.core.helpers.isValidEmail
import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.dto.LoginForm
import ip.team13.petowner.data.dto.RegisterForm
import ip.team13.petowner.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val preferences: Preferences
) : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    var navigateToHome: (() -> Unit)? = null
    var showAlert: ((String) -> Unit)? = null

    fun login() {
        val userEmail = email.get() ?: return
        val userPassword = password.get() ?: return

        if (!userEmail.isValidEmail()) return

        doLogin(userEmail, userPassword, navigateToHome)
    }

    private fun doLogin(email: String, password: String, onSuccess: (() -> Unit)? = null) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.login(
                    LoginForm(email, password, preferences.getFCMToken() ?: "")
                )
                response.userToken?.let { preferences.saveBearerToken(it) }
                response.userId?.let { preferences.saveUserId(it) }

                if (!response.userToken.isNullOrEmpty() && response.userId != null) {
                    onSuccess?.invoke()
                }
            } catch (e: Exception) {
                e.message?.logError()
                showAlert?.invoke("An error has occurred. Please try again later.")
            }
        }


    private fun doRegister(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.register(
                    RegisterForm(
                        email = email,
                        password = password,
                        tokenFCM = preferences.getFCMToken() ?: "",
                        inviteCode = null
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

    fun doGoogleAuth(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = authRepository.login(
                LoginForm(email, password, preferences.getFCMToken() ?: "")
            )

            when (response.errorCode) {
                ErrorCodes.Email_Or_Password_Invalid.ordinal -> doRegister(email, password)
                null -> {
                    response.userToken?.let { preferences.saveBearerToken(it) }
                    response.userId?.let { preferences.saveUserId(it) }

                    if (!response.userToken.isNullOrEmpty() && response.userId != null) {
                        withContext(Dispatchers.Main) {
                            navigateToHome?.invoke()
                        }
                    }
                }
                else -> withContext(Dispatchers.Main) {
                    showAlert?.invoke("An error has occurred. Please try again later.")
                }
            }

        } catch (e: Exception) {
            e.message?.logError()
            showAlert?.invoke("An error has occurred. Please try again later.")
        }
    }
}