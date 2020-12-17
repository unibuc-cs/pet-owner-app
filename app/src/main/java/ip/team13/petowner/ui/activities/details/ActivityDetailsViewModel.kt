package ip.team13.petowner.ui.activities.details

import androidx.lifecycle.ViewModel
import ip.team13.petowner.R
import ip.team13.petowner.ui.activities.list.ItemActivityDetailViewModel
import ip.team13.petowner.ui.activities.list.ItemDetailType

class ActivityDetailsViewModel(
    private val onAddActivity: () -> Unit,
    private val onCancel: () -> Unit
) : ViewModel() {

    val title = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppCyanDark_99,
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.TITLE
    )

    val description = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppGreen,
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.DESCRIPTION
    )

    val dueTime = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppYellowBright,
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.DUE_TIME
    )

    val repeat = ItemActivityDetailViewModel(
        backgroundColor = R.color.colorAppOrangeBright,
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.REPEAT
    )

    val reminder = ItemActivityDetailViewModel(
        backgroundColor = R.color.purple_200,
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.REMINDER
    )

    fun onConfirm() {
        //TODO call backend API for adding an activity
        onAddActivity.invoke()
    }

    fun onCancel() {
        onCancel.invoke()
    }

}