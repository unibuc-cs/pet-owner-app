package ip.team13.petowner.ui.pet

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.dto.PetEntryModel
import ip.team13.petowner.data.dto.PetModel
import ip.team13.petowner.data.repository.PetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetProfileViewModel(
    private val petRepository: PetRepository,
    private val petId: Int
) : BaseViewModel() {

    val pet = ObservableField<PetEntryModel>()

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