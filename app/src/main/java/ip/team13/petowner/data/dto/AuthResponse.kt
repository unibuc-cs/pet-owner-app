package ip.team13.petowner.data.dto

import com.squareup.moshi.Json

data class AuthResponse(
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "userToken")
    val userToken: String
)