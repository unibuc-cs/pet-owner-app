package ip.team13.petowner.core.helpers

import android.content.Context
import androidx.annotation.StringRes

class StringResources(private val context: Context) {

    fun getString(@StringRes resourceId: Int) = context.getString(resourceId)
}