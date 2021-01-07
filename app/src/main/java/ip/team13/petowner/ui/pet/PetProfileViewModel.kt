package ip.team13.petowner.ui.pet

import androidx.databinding.ObservableField
import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.dto.PetModel
import ip.team13.petowner.data.repository.PetRepository

class PetProfileViewModel(
    private val petRepository: PetRepository
) : BaseViewModel() {

    val pet = ObservableField<PetModel>()
}