package ip.team13.petowner.data.repository

import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.domain.UserProfile
import ip.team13.petowner.data.domain.VipInfo
import ip.team13.petowner.data.domain.emptyUserProfile
import ip.team13.petowner.data.dto.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.threeten.bp.LocalDateTime

class UserRepository(
    private val api: PetOwnerAPI,
    private val preferences: Preferences
) {

    private val mutableUser: MutableStateFlow<UserProfile> = MutableStateFlow(emptyUserProfile())

    val userFlow: StateFlow<UserProfile>
        get() = mutableUser

    suspend fun fetchOwnUserProfile() {
        mutableUser.value = api.getUser(preferences.getUserId().toString()).toUserProfile()
    }

    suspend fun getUser(userId: Int): UserProfile = api.getUser(userId.toString()).toUserProfile()

    suspend fun updateNameAndOrPhotoUrl(name: String? = null, photoUrl: String? = null) {
        api.updateNameAndOrPhotoUrl(
            preferences.getUserId().toString(),
            NamePhotoUrlModel(name, photoUrl)
        )
    }

    suspend fun createVip() {
        val vip = api.createVip(BuyVipModel(preferences.getUserId()))

        mutableUser.value = mutableUser.value.copy(
            vipInfo = VipInfo(
                vipStartDate = LocalDateTime.parse(vip.startDate.substringBeforeLast('.')),
                vipEndDate = LocalDateTime.parse(vip.endDate.substringBeforeLast('.')),
                expMultiplier = vip.expMultiplier
            )
        )
    }

    suspend fun updateTokens(tokens: Int) {
        if (api.updateTokens(preferences.getUserId().toString(), TokensModel(tokens)))
            mutableUser.value = mutableUser.value.copy(tokens = mutableUser.value.tokens - tokens)
    }

    suspend fun updateWeeklyExp(exp: Int) =
        api.updateWeeklyExp(
            userId = preferences.getUserId(),
            body = UpdateWeeklyExpRequestModel(exp)
        )
}