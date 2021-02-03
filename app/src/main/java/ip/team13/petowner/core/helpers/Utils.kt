package ip.team13.petowner.core.helpers

import android.content.Context
import androidx.appcompat.app.AlertDialog

inline fun <T: Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}


fun Context.showCustomAlert(
    title: String? = null,
    message: String,
    hasPositiveButton: Boolean = true,
    hasNegativeButton: Boolean = true,
    positiveButtonText: String = "Ok",
    negativeButtonText: String = "Cancel",
    onPositiveAction: (() -> Unit)? = null,
    onNegativeAction: (() -> Unit)? = null,
    isCancelable: Boolean = false
): AlertDialog? {
    val b = AlertDialog.Builder(this)
        .setMessage(message)
        .setCancelable(isCancelable)

    title?.let {
        b.setTitle(title)
    }

    onNegativeAction?.let {
        if (hasNegativeButton) {
            b?.setNegativeButton(negativeButtonText) { _, _ ->
                it.invoke()
            }
        }
    }

    if (hasPositiveButton) {
        b?.setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveAction?.invoke()
        }
    }

    val mAlert = b?.create() ?: return null
    mAlert.show()

    return mAlert
}