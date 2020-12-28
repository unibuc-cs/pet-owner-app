package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.LoginForm
import ip.team13.petowner.data.dto.RegisterForm

class AuthRepository(
    private val petOwnerAPI: PetOwnerAPI,
    private val preferences: Preferences
) {

    suspend fun register(email: String, password: String, inviteCode: String) {

        try {
            val response = petOwnerAPI.register(
                RegisterForm(email, password, preferences.getFCMToken() ?: "", inviteCode)
            )
            preferences.saveBearerToken(response.userToken)
            preferences.saveUserId(response.userId)
        } catch (e: Exception) {
            e.message?.logError()
        }
    }

    suspend fun login(email: String, password: String) {
        try {
            val response = petOwnerAPI.login(
                LoginForm(email, password, preferences.getFCMToken() ?: "")
            )
            preferences.saveBearerToken(response.userToken)
            preferences.saveUserId(response.userId)
        } catch (e: Exception) {
            e.message?.logError()
        }
    }

}