package ip.team13.petowner.data.repository

import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.domain.UserProfile
import ip.team13.petowner.data.dto.NamePhotoUrlModel
import ip.team13.petowner.data.dto.toUserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserRepository(
    private val api: PetOwnerAPI,
    private val preferences: Preferences
) {

    private val mutableUser: MutableStateFlow<UserProfile> = MutableStateFlow(
        UserProfile(null, null, 1, 0, null)
    )

    val userFlow: StateFlow<UserProfile>
        get() = mutableUser

    suspend fun fetchUserProfile() {
        mutableUser.value = api.getUser(preferences.getUserId().toString()).toUserProfile()
    }

    suspend fun updateNameAndOrPhotoUrl(name: String? = null, photoUrl: String? = null) {
        api.updateNameAndOrPhotoUrl(NamePhotoUrlModel(name, photoUrl))
    }
}