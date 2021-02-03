package ip.team13.petowner.ui.pet

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.core.persistence.Preferences
import ip.team13.petowner.data.domain.PetDetails
import ip.team13.petowner.data.repository.PetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetProfileViewModel(
    private val petId: Int,
    private val petRepository: PetRepository,
    private val preferences: Preferences
) : BaseViewModel() {

    val pet = ObservableField<PetDetails>()

    val happinessScore: Int
        get() = preferences.getHappinessScore(petId)

    init {
        getPet()
    }

    private fun getPet() {
        viewModelScope.launch(Dispatchers.IO) {
            val petResult = petRepository.getPet(petId)
            withContext(Dispatchers.Main) {
                pet.set(petResult)
            }
        }
    }
}