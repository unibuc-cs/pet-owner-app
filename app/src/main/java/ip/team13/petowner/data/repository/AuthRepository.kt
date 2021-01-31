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

    suspend fun register(email: String, password: String, inviteCode: String? = null): Boolean {

        try {
            val response = petOwnerAPI.register(
                RegisterForm(email, password, preferences.getFCMToken() ?: "", inviteCode)
            )
            response.userToken?.let { preferences.saveBearerToken(it) }
            response.userId?.let { preferences.saveUserId(it) }
            response.errorCode?.let { return false }

            if (!response.userToken.isNullOrEmpty() && response.userId != null) {
                return true
            }

        } catch (e: Exception) {
            e.message?.logError()
        }

        return false
    }

    suspend fun login(email: String, password: String): Boolean {
        try {
            val response = petOwnerAPI.login(
                LoginForm(email, password, preferences.getFCMToken() ?: "")
            )
            response.userToken?.let { preferences.saveBearerToken(it) }
            response.userId?.let { preferences.saveUserId(it) }

            if (!response.userToken.isNullOrEmpty() && response.userId != null) {
                return true
            }
        } catch (e: Exception) {
            e.message?.logError()
        }

        return false
    }
}