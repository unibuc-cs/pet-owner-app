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

    suspend fun register(
        email: String, password: String, inviteCode: String? = null, onSuccess: (() -> Unit)? = null
    ) {

        try {
            val response = petOwnerAPI.register(
                RegisterForm(email, password, preferences.getFCMToken() ?: "", inviteCode)
            )
            response.userToken?.let { preferences.saveBearerToken(it) }
            response.userId?.let { preferences.saveUserId(it) }

            if (!response.userToken.isNullOrEmpty() && response.userId != null) {
                onSuccess?.invoke()
            }

        } catch (e: Exception) {
            e.message?.logError()
        }
    }

    suspend fun login(email: String, password: String, onSuccess: (() -> Unit)? = null) {
        try {
            val response = petOwnerAPI.login(
                LoginForm(email, password, preferences.getFCMToken() ?: "")
            )
            response.userToken?.let { preferences.saveBearerToken(it) }
            response.userId?.let { preferences.saveUserId(it) }

            if (!response.userToken.isNullOrEmpty() && response.userId != null) {
                onSuccess?.invoke()
            }
        } catch (e: Exception) {
            e.message?.logError()
        }
    }

}