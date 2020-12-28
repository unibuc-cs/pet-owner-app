package ip.team13.petowner.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ip.team13.petowner.core.helpers.isValidEmail
import ip.team13.petowner.data.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()

    fun login() {
        val userEmail = email.get() ?: return
        val userPassword = password.get() ?: return

        if (!userEmail.isValidEmail()) return

        CoroutineScope(Dispatchers.IO).launch {
            authRepository.login(email = userEmail, password = userPassword)
        }
    }
}