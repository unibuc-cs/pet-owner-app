package ip.team13.petowner.ui.home

import androidx.lifecycle.ViewModel
import ip.team13.petowner.data.repository.UserRepository

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    lateinit var navigateToUserProfile: () -> Unit

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