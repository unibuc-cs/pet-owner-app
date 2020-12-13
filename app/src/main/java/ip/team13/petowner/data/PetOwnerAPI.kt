package ip.team13.petowner.data

import ip.team13.petowner.data.dto.RegisterForm
import retrofit2.http.Body
import retrofit2.http.POST

interface PetOwnerAPI {

    @POST("/user/register")
    fun register(@Body body: RegisterForm): String
}