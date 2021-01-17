package ip.team13.petowner.ui.cost

import androidx.databinding.Bindable
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.repository.CostTrackerRepository

class CostTrackerViewModel(private val costTrackerRepository: CostTrackerRepository) : BaseViewModel() {
    val values = arrayListOf(11F, 42F, 35F, 4F, 0F, 14F)

    @get:Bindable
    val items = costTrackerRepository.getExpenses()

}