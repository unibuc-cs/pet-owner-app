package ip.team13.petowner.ui.cost.list

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import ip.team13.petowner.R
import ip.team13.petowner.data.dto.CostTrackerCategory
import ip.team13.petowner.data.dto.CostTrackerRecyclerViewModel

class ItemCostTrackerViewModel(val costTrackerModel: CostTrackerRecyclerViewModel) {

    fun getPhotoByCategory(context: Context): Drawable? = when (costTrackerModel.category) {
        CostTrackerCategory.FOOD.title -> ContextCompat.getDrawable(
            context,
            R.drawable.ic_cost_tracker_food
        )
        CostTrackerCategory.FUN.title -> ContextCompat.getDrawable(
            context,
            R.drawable.ic_cost_tracker_fun
        )
        else -> ContextCompat.getDrawable(context, R.drawable.ic_cost_tracker_health)
    }

    fun getColorByCategory(): Int = when (costTrackerModel.category) {
        CostTrackerCategory.FOOD.title -> R.color.colorAppGreen
        CostTrackerCategory.FUN.title -> R.color.colorAppOrangeBright
        else -> R.color.colorAppYellowBright
    }


}