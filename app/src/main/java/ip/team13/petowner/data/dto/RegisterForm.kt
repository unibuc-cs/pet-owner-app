package ip.team13.petowner.data.dto

data class RegisterForm(
    val email: String,
    val password: String,
    val tokenFCM: String,
    val inviteCode: String?
)