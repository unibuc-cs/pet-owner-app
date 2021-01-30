package ip.team13.petowner.data

import com.squareup.moshi.Json
import ip.team13.petowner.data.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PetOwnerAPI {

    @POST("api/user/register")
    suspend fun register(@Body body: RegisterForm): AuthResponseModel

    @POST("api/user/login")
    suspend fun login(@Body body: LoginForm): AuthResponseModel

    @POST("api/pet")
    suspend fun addPet(@Body body: AddPetRequestModel)

    @GET("api/pet/group/{userId}")
    suspend fun getGroupPets(@Path("userId") userId: Int): List<PetEntryModel>

    @GET("/pet/group/{userId}")
    suspend fun getPetsAndActivities(@Path("userId") userId: String): List<PetEntryModel>

    @POST("api/user/leaderboards")
    suspend fun getLeaderboards(@Body body: LeaderboardRequestModel): List<LeaderboardEntryModel>

    @POST("api/activity/petactivity")
    suspend fun addActivity(@Body body: ActivityEntry)

    @GET("api/pet/{id}")
    suspend fun getPet(@Path("id") petId: Int): PetEntryModel
}