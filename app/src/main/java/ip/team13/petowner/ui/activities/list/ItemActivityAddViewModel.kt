package ip.team13.petowner.ui.activities.list

import ip.team13.petowner.data.domain.ActivityAdd

class ItemActivityAddViewModel(val model: ActivityAdd) {
    fun onAddActivity() {
        model.onAddActivity.invoke()
    }
}