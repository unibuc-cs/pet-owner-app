package ip.team13.petowner.data.repository

import ip.team13.petowner.data.PetOwnerAPI

class TipsRepository(
    private val petOwnerAPI: PetOwnerAPI
) {

    suspend fun getTips() =
        petOwnerAPI.getAllTips()
}