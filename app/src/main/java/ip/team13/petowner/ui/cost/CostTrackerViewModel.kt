package ip.team13.petowner.ui.cost

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.BR
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.dto.CostTrackerCategory
import ip.team13.petowner.data.dto.CostTrackerModel
import ip.team13.petowner.data.dto.CostTrackerRecylerViewModel
import ip.team13.petowner.data.repository.CostTrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ChartItem(
    val value: Float,
    val category: String
) {
    companion object {
        fun getPlaceholder() = ChartItem(
            Random.nextFloat() % 100,
            CostTrackerCategory.values().maxOf { it.title }.random().toString()
        )
    }
}

class CostTrackerViewModel(private val costTrackerRepository: CostTrackerRepository) :
    BaseViewModel() {
    val values = arrayListOf(
        CostTrackerModel.getPlaceholder(),
        CostTrackerModel.getPlaceholder(),
        CostTrackerModel.getPlaceholder(),
        CostTrackerModel.getPlaceholder(),
        CostTrackerModel.getPlaceholder(),
        CostTrackerModel.getPlaceholder(),
        CostTrackerModel.getPlaceholder(),
        CostTrackerModel.getPlaceholder(),
    ).groupBy(CostTrackerModel::category).map { entry ->
        ChartItem(
            value = entry.value.sumBy { it.cost.toInt() }.toFloat(),
            category = entry.key
        )
    }

    @get:Bindable
    var items = listOf<CostTrackerRecylerViewModel>()

    fun getItems() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = costTrackerRepository.getExpenses()
            withContext(Dispatchers.Main) {
                items = result
                notifyPropertyChanged(BR.items)
            }
        }
    }
}

