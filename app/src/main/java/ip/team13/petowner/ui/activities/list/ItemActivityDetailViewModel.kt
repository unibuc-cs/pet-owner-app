package ip.team13.petowner.ui.activities.list

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.addOnPropertyChanged
import java.text.SimpleDateFormat
import java.util.*

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

    fun onClick(view: View) = when (itemDetailType) {
        ItemDetailType.TITLE -> {
        }
        ItemDetailType.DESCRIPTION -> {
        }
        ItemDetailType.DUE_TIME -> {
            onShowCalendar(view.context)
        }
        ItemDetailType.REPEAT -> {
        }
        ItemDetailType.REMINDER -> {
        }
    }

    fun getFocusableState() = when (itemDetailType) {
        ItemDetailType.TITLE, ItemDetailType.DESCRIPTION -> true
        else -> false
    }

    private val calendar: Calendar = Calendar.getInstance()
    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            fieldValue.set(SimpleDateFormat("MM/dd/yyyy", Locale.US).format(calendar.time))
        }

    private fun onShowCalendar(context: Context) {
        DatePickerDialog(
            context,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}


