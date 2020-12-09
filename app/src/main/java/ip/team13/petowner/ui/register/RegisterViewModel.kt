package ip.team13.petowner.ui.register

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ip.team13.petowner.data.repository.AuthRepository

class RegisterViewModel : ViewModel() {

    private val authRepository = AuthRepository()

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

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}