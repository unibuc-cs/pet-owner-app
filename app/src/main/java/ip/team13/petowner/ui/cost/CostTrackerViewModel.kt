package ip.team13.petowner.ui.cost

import androidx.databinding.Bindable
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.repository.CostTrackerRepository
import androidx.lifecycle.ViewModel
import ip.team13.petowner.data.dto.CostItemModel
import ip.team13.petowner.data.dto.CostTrackerCategory
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

class CostTrackerViewModel(private val costTrackerRepository: CostTrackerRepository) : BaseViewModel() {
    val values = arrayListOf(11F, 42F, 35F, 4F, 0F, 14F)

    @get:Bindable
    val items = costTrackerRepository.getExpenses()

class CostTrackerViewModel : ViewModel() {
    val values = arrayListOf(
        CostItemModel.getPlaceholder(),
        CostItemModel.getPlaceholder(),
        CostItemModel.getPlaceholder(),
        CostItemModel.getPlaceholder(),
        CostItemModel.getPlaceholder(),
        CostItemModel.getPlaceholder(),
        CostItemModel.getPlaceholder(),
        CostItemModel.getPlaceholder(),
    ).groupBy(CostItemModel::category).map { entry ->
        ChartItem(
            value = entry.value.sumBy { it.cost.toInt() }.toFloat(),
            category = entry.key
        )
    }
}