package ip.team13.petowner.ui.activities.details

import androidx.lifecycle.ViewModel
import ip.team13.petowner.R
import ip.team13.petowner.data.dto.ActivityEntry
import ip.team13.petowner.ui.activities.list.ItemActivityDetailViewModel
import ip.team13.petowner.ui.activities.list.ItemDetailType
import ip.team13.petowner.ui.activities.list.RepeatType
import kotlin.random.Random

class ActivityDetailsViewModel(private val petId: Int) : ViewModel() {

    var onAddActivity: ((ActivityEntry) -> Unit)? = null
    var onCancel: (() -> Unit)? = null

    val title = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppCyanDark_99,
        iconResource = R.drawable.ic_title,
        labelResource = R.string.add_activity_title,
        itemDetailType = ItemDetailType.TITLE
    )

    val description = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppGreen,
        iconResource = R.drawable.ic_description,
        labelResource = R.string.add_activity_description,
        itemDetailType = ItemDetailType.DESCRIPTION
    )

    val dueTime = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppYellowBright,
        iconResource = R.drawable.ic_due_time,
        labelResource = R.string.add_activity_due_time,
        itemDetailType = ItemDetailType.DUE_TIME
    )

    val repeat = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppOrangeBright,
        iconResource = R.drawable.ic_repeat,
        labelResource = R.string.add_activity_repeat,
        itemDetailType = ItemDetailType.REPEAT
    )

    val reminder = ItemActivityDetailViewModel(
        backgroundColor = R.color.purple_200,
        iconResource = R.drawable.ic_reminder,
        labelResource = R.string.add_activity_reminder,
        itemDetailType = ItemDetailType.REMINDER
    )

    fun onConfirm() {

        onAddActivity?.invoke(
            ActivityEntry(
                title = title.fieldValue.get(),
                description = description.fieldValue.get(),
                dueTime = dueTime.fieldValue.get(),
                petId = petId,
                expPoints = getExpPoints(repeat.fieldValue.get()),
                recurring = repeat.fieldValue.get().equals(RepeatType.NEVER.title),
                recurringInterval = getRecurringInterval(repeat.fieldValue.get())
            )
        )
    }

    internal fun getRecurringInterval(value: String?) = when (value) {
        RepeatType.NEVER.title -> 0
        RepeatType.DAILY.title -> 1
        RepeatType.WEEKLY.title -> 7
        RepeatType.MONTHLY.title -> 30
        else -> -1
    }

    internal fun getExpPoints(value: String?) = when (value) {
        RepeatType.NEVER.title -> RepeatType.NEVER.expPoints
        RepeatType.DAILY.title -> RepeatType.DAILY.expPoints
        RepeatType.WEEKLY.title -> RepeatType.WEEKLY.expPoints
        RepeatType.MONTHLY.title -> RepeatType.MONTHLY.expPoints
        else -> 0
    }

    fun onCancel() {
        onCancel?.invoke()
    }
}