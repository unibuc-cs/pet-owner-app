package ip.team13.petowner.ui.activities.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.ObservableField

enum class ItemDetailType {
    DESCRIPTION,
    DUE_TIME,
    REPEAT,
    REMINDER
}

class ItemActivityDetailViewModel(
    @DrawableRes val iconResource: Int,
    @StringRes val labelResource: Int,
    val fieldValue: ObservableField<String> = ObservableField<String>(),
    private val itemDetailType: ItemDetailType
) {

    fun onClick() = when (itemDetailType) {
        ItemDetailType.DESCRIPTION -> {
        }
        ItemDetailType.DUE_TIME -> {
        }
        ItemDetailType.REPEAT -> {
        }
        ItemDetailType.REMINDER -> {
        }
    }
}

