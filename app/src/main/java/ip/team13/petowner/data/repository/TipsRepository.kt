package ip.team13.petowner.data.repository

import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.toTip

class TipsRepository(
    private val petOwnerAPI: PetOwnerAPI
) {

    suspend fun getTips() =
        petOwnerAPI.getAllTips().map {
            it.toTip()
        }
}