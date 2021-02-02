package ip.team13.petowner.data.dto

import com.squareup.moshi.Json
import ip.team13.petowner.data.domain.GroupUser
import ip.team13.petowner.data.domain.UserProfile
import ip.team13.petowner.data.domain.VipInfo
import org.threeten.bp.LocalDateTime

data class ShortUserWrapperModel(
    @field:Json(name = "userId")
    val userId: Int,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "photo")
    val photo: String?,
    @field:Json(name = "vipId")
    val vipId: Int?
)

data class UserWrapperModel(
    @field:Json(name = "groupId")
    val groupId: Int,
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
    val startDate: String,
    @field:Json(name = "endDate")
    val endDate: String,
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

data class BuyVipModel(
    @field:Json(name = "id")
    val id: Int
)

data class TokensModel(
    @field:Json(name = "tokens")
    val tokens: Int
)

fun UserWrapperModel.toUserProfile() =
    UserProfile(
        groupId = groupId,
        name = name,
        photoUrl = photoUrl,
        level = level,
        tokens = tokens,
        vipInfo = vip?.let { vipModel ->
            VipInfo(
                vipStartDate = LocalDateTime.parse(vipModel.startDate.substringBeforeLast('.')),
                vipEndDate = LocalDateTime.parse(vipModel.endDate.substringBeforeLast('.')),
                expMultiplier = vipModel.expMultiplier
            )
        }
    )

fun ShortUserWrapperModel.toGroupUser() =
    GroupUser(
        userId = userId,
        name = name,
        photoUrl = photo,
        isVip = vipId != null
    )