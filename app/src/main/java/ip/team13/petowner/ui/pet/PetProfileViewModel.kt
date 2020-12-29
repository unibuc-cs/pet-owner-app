package ip.team13.petowner.ui.pet

import ip.team13.petowner.core.BaseViewModel
import ip.team13.petowner.data.repository.PetRepository

class PetProfileViewModel(
    private val petRepository: PetRepository
) : BaseViewModel() {

    lateinit var navigateBack: () -> Unit

    val imageUrl: String
        get() = petRepository.imageUrl
}