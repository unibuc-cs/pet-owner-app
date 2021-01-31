package ip.team13.petowner.data.domain

import org.threeten.bp.LocalDateTime

data class UserProfile(
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