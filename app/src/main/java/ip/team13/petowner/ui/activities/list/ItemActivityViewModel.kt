package ip.team13.petowner.ui.activities.list

import android.view.View
import androidx.databinding.ObservableField
import ip.team13.petowner.core.helpers.showCustomAlert
import ip.team13.petowner.data.domain.ActivityItem

class ItemActivityViewModel(
    val activity: ActivityItem
) {
    val isCompleted = ObservableField(false)

    fun markAsCompleted(view: View) {
        view.context.showCustomAlert(
            message = "Mark as completed?",
            onPositiveAction = {
                isCompleted.set(true)
                activity.updateTokensAndExp.invoke()
            },
            negativeButtonText = "Cancel",
            onNegativeAction = { }
        )
    }
}