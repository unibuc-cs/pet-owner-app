package ip.team13.petowner.ui.user

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.BR
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.helpers.StringResources
import ip.team13.petowner.core.helpers.vipTimeLeft
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.domain.UserProfile
import ip.team13.petowner.data.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.threeten.bp.format.DateTimeFormatter

private const val VIP_PRICE = 349

class UserProfileViewModel(
    val isOwnUserProfile: Boolean,
    private val repository: UserRepository,
    private val preferences: Preferences,
    private val stringResources: StringResources
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            repository.userFlow.collect {
                user = it
                notifyChange()
            }
        }
    }

    lateinit var navigateBack: () -> Unit
    lateinit var navigateToLogin: () -> Unit

    private var user: UserProfile = repository.userFlow.value

    @get:Bindable
    val photoUrl: String?
        get() = user.photoUrl

    @get:Bindable
    val name: String
        get() = user.name ?: ""

    @get:Bindable
    var editableName: String = name

    @get:Bindable
    val level: String
        get() = stringResources.getString(R.string.level) + user.level.toString()

    @get:Bindable
    val tokens: String
        get() = user.tokens.toString()

    @get:Bindable
    val isVip: Boolean
        get() = user.vipInfo != null

    @get:Bindable
    val vipTimeLeft: String?
        get() = user.vipInfo?.vipEndDate?.vipTimeLeft()

    @get:Bindable
    val vipDurationText: String
        get() = "${user.vipInfo?.vipStartDate?.format(DateTimeFormatter.ISO_LOCAL_DATE)} - " +
                "${user.vipInfo?.vipEndDate?.format(DateTimeFormatter.ISO_LOCAL_DATE)}"

    @get:Bindable
    val expMultiplier: String
        get() = user.vipInfo?.expMultiplier?.toString() ?: ""

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

    fun logout() {
        preferences.removeSession()
        navigateToLogin()
    }

    fun onSaveClick() {
        viewModelScope.launch {
            repository.updateNameAndOrPhotoUrl(name = editableName)
        }
        navigateBack()
    }

    fun onBuyVipClick() {
        viewModelScope.launch {
            if (user.tokens >= VIP_PRICE) {
                repository.createVip()
                repository.updateTokens(user.tokens - VIP_PRICE)
            }
        }
    }
}