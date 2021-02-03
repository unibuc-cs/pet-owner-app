package ip.team13.petowner.ui.activities.list

import android.view.View
import androidx.databinding.ObservableField
import ip.team13.petowner.core.helpers.showCustomAlert
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.domain.ActivityItem
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class ItemActivityViewModel(
    val activity: ActivityItem,
    val preferences: Preferences
) {
    val isCompleted = ObservableField(false)

    val formattedDate = activity.model.dueTime?.let { dueTime ->
        LocalDateTime.parse(dueTime).format(DateTimeFormatter.ISO_LOCAL_DATE)
    } ?: ""

    fun markAsCompleted(view: View) {
        view.context.showCustomAlert(
            message = "Mark as completed?",
            onPositiveAction = {
                isCompleted.set(true)
                preferences.increaseHappinessScore(activity.model.petId)
                activity.updateTokensAndExp.invoke()
            },
            negativeButtonText = "Cancel",
            onNegativeAction = { }
        )
    }
}