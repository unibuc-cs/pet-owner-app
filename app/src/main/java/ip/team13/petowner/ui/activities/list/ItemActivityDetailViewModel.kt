package ip.team13.petowner.ui.activities.list

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.ObservableField

enum class ItemDetailType {
    TITLE,
    DESCRIPTION,
    DUE_TIME,
    REPEAT,
    REMINDER
}

class ItemActivityDetailViewModel(
    @ColorRes val backgroundColor: Int,
    @DrawableRes val iconResource: Int,
    @StringRes val labelResource: Int,
    val fieldValue: ObservableField<String> = ObservableField<String>(),
    private val itemDetailType: ItemDetailType
) {

    fun onClick() = when (itemDetailType) {
        ItemDetailType.TITLE -> {
        }
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

