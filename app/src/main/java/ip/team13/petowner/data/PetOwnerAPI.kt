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
    suspend fun updateNameAndOrPhotoUrl(
        @Path("userId") userId: String,
        @Body body: NamePhotoUrlModel
    ): Boolean

    @POST("api/vip")
    suspend fun createVip(@Body userId: BuyVipModel): VipModel

    @PATCH("api/gamification/{userId}/tokens")
    suspend fun updateTokens(@Path("userId") userId: String, @Body body: TokensModel): Boolean

    // ******* Leaderboards *******
    @POST("api/user/leaderboards")
    suspend fun getLeaderboards(@Body body: LeaderboardRequestModel): List<LeaderboardEntryModel>

    // ******* Pet *******
    @GET("api/pet/group/{userId}")
    suspend fun getGroupPets(@Path("userId") userId: Int): List<PetEntryModel>

    @GET("api/pet/{id}")
    suspend fun getPet(@Path("id") petId: Int): PetEntryModel

    @POST("api/pet")
    suspend fun addPet(@Body body: AddPetRequestModel)

    @PATCH("api/pet/{petId}")
    suspend fun updatePet(@Path("petId") petId: Int, @Body body: PetUpdateModel)

    // ******* Activities *******
    @GET("/pet/group/{userId}")
    suspend fun getPetsAndActivities(@Path("userId") userId: String): List<PetEntryModel>

    @POST("api/activity/petactivity")
    suspend fun addActivity(@Body body: ActivityEntry)

    // ******* Group *******
    @GET("api/group/{groupId}")
    suspend fun getGroup(@Path("groupId") userId: Int): GroupWrapperModel

    // ******* Cost Item *******
    @POST("api/item/{userId}")
    suspend fun addItem(@Path("userId") userId: Int, @Body body: CostItemModel)

    @DELETE("api/item/{itemId}")
    suspend fun deleteItem(itemId: Int)
}