package ip.team13.petowner.core.persistence

import android.content.Context
import android.content.SharedPreferences
import ip.team13.petowner.R
import ip.team13.petowner.core.helpers.AppConstants

class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    fun getFCMToken() = preferences.getString(AppConstants.SharedPrefKeys.ARG_FCM_TOKEN, null)

    fun getBearerToken() =
        preferences.getString(AppConstants.SharedPrefKeys.ARG_BEARER_TOKEN, null);

    fun saveBearerToken(token: String) = with(preferences.edit()) {
        putString(AppConstants.SharedPrefKeys.ARG_BEARER_TOKEN, token)
        apply()
    }

    fun getUserId() =
        preferences.getInt(AppConstants.SharedPrefKeys.ARG_USER_ID, -1);

    fun saveUserId(userId: Int) = with(preferences.edit()) {
        putInt(AppConstants.SharedPrefKeys.ARG_USER_ID, userId)
        apply()
    }

    fun removeSession() = with(preferences.edit()) {
        remove(AppConstants.SharedPrefKeys.ARG_BEARER_TOKEN)
        remove(AppConstants.SharedPrefKeys.ARG_USER_ID)
        apply()
    }

    fun saveGroupId(groupId: Int) = with(preferences.edit()) {
        putInt(AppConstants.SharedPrefKeys.ARG_GROUP_ID, groupId)
        apply()
    }

    fun getGroupId() =
        preferences.getInt(AppConstants.SharedPrefKeys.ARG_GROUP_ID, -1)

}