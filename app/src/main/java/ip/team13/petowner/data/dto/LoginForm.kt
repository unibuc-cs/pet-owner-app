package ip.team13.petowner.data.dto

data class LoginForm(
    val email: String,
    val password: String,
    val tokenFCM: String,
)