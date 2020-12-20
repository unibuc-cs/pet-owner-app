package ip.team13.petowner.ui.user

import androidx.lifecycle.ViewModel
import ip.team13.petowner.data.repository.UserRepository

class UserProfileViewModel(private val repository: UserRepository) : ViewModel() {

    val imageUrl: String
        get() = repository.imageUrl

    val name: String
        get() = repository.name

    val level: String
        get() = repository.level

    val weeklyExp: String
        get() = repository.weeklyExp
}