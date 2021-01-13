package ip.team13.petowner.core.helpers

import android.app.AlertDialog
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

fun Context.showAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String?,
    negativeButtonText: String? = null,
    onPositiveAction: (() -> Unit)? = null,
    onNegativeAction: (() -> Unit)? = null
) {
    val alertDialogBuilder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)

    positiveButtonText?.let {
        alertDialogBuilder.setPositiveButton(it) { dialog, id ->
            onPositiveAction?.invoke()
            dialog.cancel()
        }
    }
    negativeButtonText?.let {
        alertDialogBuilder.setNegativeButton(it) { dialog, id ->
            onNegativeAction?.invoke()
            dialog.cancel()
        }
    }

    alertDialogBuilder.create().show()
}