package ip.team13.petowner.ui.activities.viewModels

import ip.team13.petowner.ui.activities.models.ActivityAdd

class ItemActivityAddViewModel(val model: ActivityAdd) {
    fun onAddActivity() {
        model.onAddActivity.invoke()
    }
}