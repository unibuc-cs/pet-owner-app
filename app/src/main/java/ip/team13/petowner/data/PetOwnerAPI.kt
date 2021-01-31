package ip.team13.petowner.data

import ip.team13.petowner.data.dto.*
import retrofit2.http.*

interface PetOwnerAPI {

    // ******* Auth *******
    @POST("api/user/register")
    suspend fun register(@Body body: RegisterForm): AuthResponseModel

    @POST("api/user/login")
    suspend fun login(@Body body: LoginForm): AuthResponseModel

    // ******* User *******
    @GET("api/vip/{userId}")
    suspend fun getUser(@Path("userId") userId: String): UserWrapperModel

    @PATCH("api/user/{userId}")
    suspend fun updateNameAndOrPhotoUrl(@Body body: NamePhotoUrlModel): Boolean

    // ******* Leaderboards *******
    @POST("api/user/leaderboards")
    suspend fun getLeaderboards(@Body body: LeaderboardRequestModel): List<LeaderboardEntryModel>

    // ******* Pets & Activities *******
    @GET("api/pet/group/{userId}")
    suspend fun getPetsAndActivities(@Path("userId") userId: String): List<PetEntryModel>

    @POST("api/activity/petactivity")
    suspend fun addActivity(@Body body: ActivityEntry)

    @GET("api/pet/{id}")
    suspend fun getPet(@Path("id") petId: Int): PetEntryModel
}