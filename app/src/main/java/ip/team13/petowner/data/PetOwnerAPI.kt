package ip.team13.petowner.data

import ip.team13.petowner.data.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PetOwnerAPI {

    @POST("/user/register")
    suspend fun register(@Body body: RegisterForm): AuthResponseModel

    @POST("/user/login")
    suspend fun login(@Body body: LoginForm): AuthResponseModel

    @GET("/pet/group/{userId}")
    suspend fun getPetsAndActivities(@Path("userId") userId: String): List<PetEntryModel>
}