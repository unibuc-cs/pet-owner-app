package ip.team13.petowner.ui.home

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.StringResources
import ip.team13.petowner.core.helpers.vipTimeLeft
import ip.team13.petowner.data.domain.UserProfile
import ip.team13.petowner.data.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository,
    private val stringResources: StringResources
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            repository.fetchUserProfile()

            repository.userFlow.collect {
                user = it
                notifyChange()
            }
        }
    }

    lateinit var navigateToUserProfile: () -> Unit

    private var user: UserProfile = repository.userFlow.value

    @get:Bindable
    val name: String
        get() = user.name?.let {
            stringResources.getString(R.string.hello) + it
        } ?: stringResources.getString(R.string.empty_name)

    @get:Bindable
    val profileImageUrl: String?
        get() = user.photoUrl

    @get:Bindable
    val level: String
        get() = stringResources.getString(R.string.level) + user.level

    @get:Bindable
    val tokens: String
        get() = user.tokens.toString()

    @get:Bindable
    val isVip: Boolean
        get() = user.vipInfo != null

    @get:Bindable
    val vipTimeLeft: String?
        get() = user.vipInfo?.vipEndDate?.vipTimeLeft()

    fun onProfileHeaderClick() {
        navigateToUserProfile()
    }
}