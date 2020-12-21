package ip.team13.petowner.core.helpers

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

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