package ip.team13.petowner.data

import com.squareup.moshi.Json
import ip.team13.petowner.data.dto.*
import retrofit2.http.*

interface PetOwnerAPI {

    // Auth
    @POST("api/user/register")
    suspend fun register(@Body body: RegisterForm): AuthResponseModel

    @POST("api/user/login")
    suspend fun login(@Body body: LoginForm): AuthResponseModel

    // User
    @POST("api/user/leaderboards")
    suspend fun getLeaderboards(@Body body: LeaderboardRequestModel): List<LeaderboardEntryModel>

    // Pet
    @POST("api/pet")
    suspend fun addPet(@Body body: AddPetRequestModel)

    @GET("api/pet/group/{userId}")
    suspend fun getGroupPets(@Path("userId") userId: Int): List<PetEntryModel>

    @GET("api/pet/{id}")
    suspend fun getPet(@Path("id") petId: Int): PetEntryModel

    // Activities
    @GET("/pet/group/{userId}")
    suspend fun getPetsAndActivities(@Path("userId") userId: String): List<PetEntryModel>

    @POST("api/activity/petactivity")
    suspend fun addActivity(@Body body: ActivityEntry)

    // Cost Item
    @POST("api/item/{userId}")
    suspend fun addItem(@Path("userId") userId: Int, @Body body: CostItemModel)

    @DELETE("api/item/{itemId}")
    suspend fun deleteItem(itemId: Int)
}