package ip.team13.petowner.data.dto.register

data class RegisterForm(
    val email: String,
    val password: String,
    val tokenFCM: String,
    val inviteCode: String?
)