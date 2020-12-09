package ip.team13.petowner.helpers

import android.content.Context
import android.util.Log
import android.widget.Toast
import ip.team13.petowner.BuildConfig

fun String.logError(TAG: String? = null) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, this)
    }
}

fun String.toastShortError(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(context, this, duration).show()
    }
}

fun String.toastLongError(context: Context, duration: Int = Toast.LENGTH_LONG) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(context, this, duration).show()
    }
}
