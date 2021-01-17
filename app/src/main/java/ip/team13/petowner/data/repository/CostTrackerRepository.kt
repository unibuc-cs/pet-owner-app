package ip.team13.petowner.data.repository

import ip.team13.petowner.data.PetOwnerAPI
import ip.team13.petowner.data.dto.CostTrackerModel

class CostTrackerRepository(private val petOwnerAPI: PetOwnerAPI) {

    fun getExpenses(): List<CostTrackerModel> {
        return arrayListOf(
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
            CostTrackerModel("Wiskas", "Food", 55.46),
        )
    }
}