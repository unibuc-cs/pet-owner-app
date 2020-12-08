package ip.team13.petowner.helpers

import android.content.Context
import ip.team13.petowner.R

fun Context.saveFcmToken(token: String) =
    getSharedPreferences(
        getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )?.let { sharedPreferences ->
        with(sharedPreferences.edit()) {
            putString(AppConstants.SharedPrefKeys.ARG_FCM_TOKEN, token)
            apply()
        }
    }

fun Context.getFcmToken() =
    getSharedPreferences(
        getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )?.getString(AppConstants.SharedPrefKeys.ARG_FCM_TOKEN, null)