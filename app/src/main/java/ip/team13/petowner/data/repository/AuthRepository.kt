package ip.team13.petowner.data.repository

import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.LoginForm
import ip.team13.petowner.data.dto.RegisterForm

class AuthRepository(
    private val petOwnerAPI: PetOwnerAPI
) {

    suspend fun register(registerForm: RegisterForm) = petOwnerAPI.register(registerForm)

    suspend fun login(loginForm: LoginForm) = petOwnerAPI.login(loginForm)

}