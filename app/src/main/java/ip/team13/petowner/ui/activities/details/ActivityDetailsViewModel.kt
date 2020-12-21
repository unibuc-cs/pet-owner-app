package ip.team13.petowner.ui.activities.details

import androidx.lifecycle.ViewModel
import ip.team13.petowner.R
import ip.team13.petowner.data.dto.ActivityEntryModel
import ip.team13.petowner.ui.activities.list.ItemActivityDetailViewModel
import ip.team13.petowner.ui.activities.list.ItemDetailType
import kotlin.random.Random

class ActivityDetailsViewModel(
    private val onAddActivity: (ActivityEntryModel) -> Unit,
    private val onCancel: () -> Unit
) : ViewModel() {

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

        //TODO call backend API for adding an activity
        onAddActivity.invoke(
            ActivityEntryModel(
                id = "",
                title = title.fieldValue.get(),
                description = description.fieldValue.get(),
                dueTime = dueTime.fieldValue.get(),
                expPoints = Random.nextInt() % 100,
                petId = ""
            )
        )
    }

    fun onCancel() {
        onCancel.invoke()
    }

}