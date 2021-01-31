package ip.team13.petowner.ui.cost

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