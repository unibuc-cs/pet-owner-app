package ip.team13.petowner.ui.activities.list

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import java.text.SimpleDateFormat
import java.util.*

enum class ItemDetailType {
    TITLE,
    DESCRIPTION,
    DUE_TIME,
    REPEAT,
    REMINDER
}

enum class RepeatType(val title: String, val value: Int, val expPoints: Int, val tokens: Int) {
    NEVER("Never", 0, 0, 0),
    DAILY("Daily", 1, 15, 10),
    WEEKLY("Weekly", 7, 20, 15),
    MONTHLY("Monthly", 30, 50, 40);
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
            onShowRepeatDialog(
                view.context,
                arrayOf(
                    RepeatType.NEVER.title,
                    RepeatType.DAILY.title,
                    RepeatType.WEEKLY.title,
                    RepeatType.MONTHLY.title
                ),
                "Select repeat time"
            )
        }
        ItemDetailType.REMINDER -> {
            onShowRepeatDialog(
                view.context,
                arrayOf("5 Minutes before", "10 Minutes before", "30 Minutes before"),
                "Select notification time"
            )
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

            fieldValue.set(
                SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.US
                ).format(calendar.time)
            )
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

    private fun onShowRepeatDialog(context: Context, choices: Array<CharSequence>, title: String) {

        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setSingleChoiceItems(
            choices, -1
        ) { dialog, item ->
            fieldValue.set(choices[item].toString())
            dialog.dismiss()
        }
        val alert: AlertDialog = alertDialogBuilder.create()
        alert.show()
    }
}


