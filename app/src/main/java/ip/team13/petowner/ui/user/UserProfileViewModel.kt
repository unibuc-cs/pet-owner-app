package ip.team13.petowner.ui.user

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.Bindable
import ip.team13.petowner.BR
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.repository.UserRepository

class UserProfileViewModel(
    val isOwnUserProfile: Boolean,
    private val repository: UserRepository,
    private val preferences: Preferences
) : BaseViewModel() {

    lateinit var navigateBack: () -> Unit
    lateinit var navigateToLogin: () -> Unit

    val imageUrl: String
        get() = repository.imageUrl

    val name: String
        get() = repository.name

    @get:Bindable
    var editableName: String = name

    val level: String
        get() = repository.level

    val weeklyExp: String
        get() = repository.weeklyExp

    val isVip: Boolean
        get() = repository.isVip

    @get:Bindable
    val isSaveButtonActive: Boolean
        get() = editableName != name // && editableImage != image

    val nameChangedWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            notifyPropertyChanged(BR.saveButtonActive)
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    fun onSaveClick() {
        repository.name = editableName
        navigateBack()
    }

    fun logout() {
        preferences.removeSession()
        navigateToLogin()
    }
}