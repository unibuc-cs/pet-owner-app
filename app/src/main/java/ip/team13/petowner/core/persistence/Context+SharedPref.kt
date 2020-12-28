package ip.team13.petowner.core.persistence

import android.content.Context
import ip.team13.petowner.R
import ip.team13.petowner.core.helpers.AppConstants

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