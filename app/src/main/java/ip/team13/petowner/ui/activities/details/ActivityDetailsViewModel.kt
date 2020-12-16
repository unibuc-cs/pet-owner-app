package ip.team13.petowner.ui.activities.details

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ip.team13.petowner.R
import ip.team13.petowner.ui.activities.list.ItemActivityDetailViewModel
import ip.team13.petowner.ui.activities.list.ItemDetailType

class ActivityDetailsViewModel : ViewModel() {

    val description = ItemActivityDetailViewModel(
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.DESCRIPTION
    )

    val dueTime = ItemActivityDetailViewModel(
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.DUE_TIME
    )

    val repeat = ItemActivityDetailViewModel(
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.REPEAT
    )

    val reminder = ItemActivityDetailViewModel(
        iconResource = R.drawable.ic_group,
        labelResource = R.string.app_name,
        itemDetailType = ItemDetailType.REMINDER
    )
}