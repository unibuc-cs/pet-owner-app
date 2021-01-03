package ip.team13.petowner.data.dto

import com.squareup.moshi.Json

data class AuthResponseModel(
    @field:Json(name = "userid")
    val userId: Int?,
    @field:Json(name = "usertoken")
    val userToken: String?
)