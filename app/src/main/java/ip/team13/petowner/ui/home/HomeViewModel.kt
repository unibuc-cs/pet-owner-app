package ip.team13.petowner.ui.home

import androidx.lifecycle.ViewModel
import ip.team13.petowner.R
import ip.team13.petowner.core.helpers.StringResources
import ip.team13.petowner.data.repository.UserRepository

class HomeViewModel(
    private val repository: UserRepository,
    private val stringResources: StringResources
) : ViewModel() {

    lateinit var navigateToUserProfile: () -> Unit

    val name: String
        get() = stringResources.getString(R.string.hello) + repository.name

    val profileImageUrl: String
        get() = repository.imageUrl

    val profileLevel: String
        get() = repository.level

    val profileWeeklyExp: String
        get() = repository.weeklyExp

    fun onProfileHeaderClick() {
        navigateToUserProfile()
    }
}