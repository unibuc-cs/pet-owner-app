package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import ip.team13.petowner.data.domain.UserProfile
import ip.team13.petowner.data.domain.VipInfo
import org.threeten.bp.LocalDateTime

data class UserWrapperModel(
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "photo")
    val photoUrl: String?,
    @field:Json(name = "level")
    val level: Int,
    @field:Json(name = "tokens")
    val tokens: Int,
    @field:Json(name = "vip")
    val vip: VipModel?
)

data class VipModel(
    @field:Json(name = "startDate")
    val startDate: LocalDateTime,
    @field:Json(name = "endDate")
    val endDate: LocalDateTime,
    @field:Json(name = "expMultiplier")
    val expMultiplier: Double,
    @field:Json(name = "user")
    val user: UserModel
)

data class UserModel(
    @field:Json(name = "groupId")
    val groupId: Int,
    @field:Json(name = "level")
    val level: LevelWrapperModel
)

data class LevelWrapperModel(
    @field:Json(name = "experience")
    val totalExperience: Int,
    @field:Json(name = "weeklyExp")
    val weeklyExperience: Int
)

data class NamePhotoUrlModel(
    val name: String?,
    val photoUrl: String?
)

fun UserWrapperModel.toUserProfile() =
    UserProfile(
        name = name,
        photoUrl = photoUrl,
        level = level,
        tokens = tokens,
        vipInfo = vip?.let { vipModel ->
            VipInfo(
                vipStartDate = vipModel.startDate,
                vipEndDate = vipModel.endDate,
                expMultiplier = vipModel.expMultiplier
            )
        }
    )