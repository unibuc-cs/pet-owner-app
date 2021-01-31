package ip.team13.petowner.data.repository

import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.CostItemModel

class CostTrackerRepository(
    private val api: PetOwnerAPI,
    private val preferences: Preferences
) {

    suspend fun addCostItem(costItem: CostItemModel) =
        api.addItem(preferences.getUserId(), costItem)
}