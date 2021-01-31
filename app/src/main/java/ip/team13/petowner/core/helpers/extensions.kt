package ip.team13.petowner.core.helpers

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit

fun EditText.closeOnDone() {
    setOnEditorActionListener { _, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE ||
            (event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
        ) {
            hideSoftKeyboard()
            return@setOnEditorActionListener true
        }

        false
    }
}

fun View.hideSoftKeyboard() {
    clearFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun LocalDateTime.vipTimeLeft(): String {
    val now = LocalDateTime.now()

    return when {
        now.until(this, ChronoUnit.DAYS) > 0 -> "${now.until(this, ChronoUnit.DAYS)} days"
        now.until(this, ChronoUnit.HOURS) > 0 -> "${now.until(this, ChronoUnit.HOURS)} h"
        now.until(this, ChronoUnit.MINUTES) > 0 -> "${now.until(this, ChronoUnit.MINUTES)} m"
        now.until(this, ChronoUnit.SECONDS) > 0 -> "${now.until(this, ChronoUnit.SECONDS)} s"
        else -> throw IllegalStateException("Bad VIP time left")
    }
}