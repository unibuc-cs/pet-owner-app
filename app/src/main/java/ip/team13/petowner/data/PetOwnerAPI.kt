package ip.team13.petowner.data

import ip.team13.petowner.data.dto.LoginForm
import ip.team13.petowner.data.dto.RegisterForm
import ip.team13.petowner.data.dto.AuthResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface PetOwnerAPI {

    @POST("api/user/register")
    suspend fun register(@Body body: RegisterForm): AuthResponseModel

    @POST("api/user/login")
    suspend fun login(@Body body: LoginForm): AuthResponseModel
}