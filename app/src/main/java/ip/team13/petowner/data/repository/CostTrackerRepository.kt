package ip.team13.petowner.data.repository

import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.CostItemPeriod
import ip.team13.petowner.data.dto.CostTrackerModel
import ip.team13.petowner.data.dto.CostTrackerRecylerViewModel
import java.lang.Exception

class CostTrackerRepository(
    private val api: PetOwnerAPI,
    private val preferences: Preferences
) {

    suspend fun addCostItem(costTracker: CostTrackerModel) =
        api.addItem(preferences.getUserId(), costTracker)

    suspend fun getExpenses(): List<CostTrackerRecylerViewModel> {
        return try {
            api.getItems(
                preferences.getUserId(),
                CostItemPeriod("2019-01-31T23:20:36Z", "2022-01-31T23:20:36Z")
            )
        } catch (ex: Exception) {
            ex.message?.logError()
            ArrayList()
        }
    }
}
