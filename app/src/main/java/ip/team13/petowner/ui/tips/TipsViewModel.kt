package ip.team13.petowner.ui.tips

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.data.domain.Tip
import ip.team13.petowner.data.repository.TipsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TipsViewModel(
    private val tipsRepository: TipsRepository
) : BaseViewModel() {

    @get:Bindable
    var tips: List<Tip> = arrayListOf()

    init {
        getTips()
    }

    private fun getTips() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val tipsResponse = tipsRepository.getTips()
            withContext(Dispatchers.Main) {
                tips = tipsResponse
                notifyPropertyChanged(BR.tips)
            }
        } catch (e: Exception) {
            e.message?.logError()
        }
    }

}