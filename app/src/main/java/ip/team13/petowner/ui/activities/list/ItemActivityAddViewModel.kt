package ip.team13.petowner.ui.activities.list

import ip.team13.petowner.R
import ip.team13.petowner.data.domain.ActivityAdd

class ItemActivityAddViewModel(val model: ActivityAdd) {

    val whiteColor = R.color.white

    fun onAddActivity() {
        model.onAddActivity.invoke()
    }
}