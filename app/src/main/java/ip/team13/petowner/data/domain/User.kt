package ip.team13.petowner.data.domain

import org.threeten.bp.LocalDateTime

data class UserProfile(
    val groupId: Int,
    val name: String?,
    val photoUrl: String?,
    val level: Int,
    val tokens: Int,
    val vipInfo: VipInfo?
)

data class VipInfo(
    val vipStartDate: LocalDateTime,
    val vipEndDate: LocalDateTime,
    val expMultiplier: Double
)

data class GroupUser(
    val userId: Int,
    val name: String?,
    val photoUrl: String?,
    val isVip: Boolean
)

fun emptyUserProfile() = UserProfile(0, null, null, 1, 0, null)