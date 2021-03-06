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

    @PATCH("api/gamification/{userId}/weeklyexp")
    suspend fun updateWeeklyExp(
        @Path("userId") userId: Int,
        @Body body: UpdateWeeklyExpRequestModel
    )
    // ******* Leaderboards *******
    @POST("api/user/leaderboards")
    suspend fun getLeaderboards(@Body body: LeaderboardRequestModel): List<LeaderboardEntryModel>

    // ******* Pet *******
    @POST("api/pet")
    suspend fun addPet(@Body body: AddPetRequestModel)

    @PATCH("api/pet/{petId}")
    suspend fun updatePet(@Path("petId") petId: Int, @Body body: PetUpdateModel)

    @GET("api/pet/group/{userId}")
    suspend fun getGroupPets(@Path("userId") userId: Int): List<PetEntryModel>

    @GET("api/activity/{petId}/activities")
    suspend fun getPetsActivities(@Path("petId") petId: Int): List<ActivityEntry>

    @GET("api/pet/{id}")
    suspend fun getPet(@Path("id") petId: Int): PetEntryModel

    // Activities
    @POST("api/activity/petactivity")
    suspend fun addPetActivity(@Body body: PetActivityRequestModel)

    @POST("api/activity/activity")
    suspend fun addActivity(@Body body: ActivityEntry): AddActivityResponseModel

    @POST("api/activity/attach")
    suspend fun attachActivity(@Body body: AttachActivityRequestModel)

    @DELETE("api/activity/petactivity/{petId}/{activityId}")
    suspend fun deleteActivity(@Path("petId") petId: Int, @Path("activityId") activityId: Int)

    // ******* Group *******
    @GET("api/group/{groupId}")
    suspend fun getGroup(@Path("groupId") userId: Int): GroupWrapperModel

    // ******* Cost Item *******
    @POST("api/item/{userId}")
    suspend fun addItem(@Path("userId") userId: Int, @Body body: CostTrackerModel)

    @DELETE("api/item/{itemId}")
    suspend fun deleteItem(itemId: Int)

    @POST("/api/item/all/{groupid}")
    suspend fun getItems(@Path("groupid") groupId: Int, @Body period: CostItemPeriod): List<CostTrackerRecyclerViewModel>

    // Tips
    @GET("api/tip")
    suspend fun getAllTips(): List<TipModel>
}