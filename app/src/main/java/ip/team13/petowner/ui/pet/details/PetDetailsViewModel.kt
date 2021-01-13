package ip.team13.petowner.ui.pet.details

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.data.dto.AddPetModel
import ip.team13.petowner.data.repository.PetRepository
import kotlinx.coroutines.launch

class PetDetailsViewModel(
    private val groupId: String,
    private val petRepository: PetRepository
) : ViewModel() {

    var navigateBack: (() -> Unit)? = null
    var showAlert: ((String) -> Unit)? = null

    val name = ObservableField("")
    val species = ObservableField("")
    val race = ObservableField("")
    val weight = ObservableField("")
    val age = ObservableField("")

    private fun fieldsAreValid(): Boolean {
        if (name.get()?.trim().isNullOrEmpty()) {
            showAlert?.invoke("Name is invalid!")
            return false
        }
        if (species.get()?.trim().isNullOrEmpty()) {
            showAlert?.invoke("Species is invalid!")
            return false
        }
        if (race.get()?.trim().isNullOrEmpty()) {
            showAlert?.invoke("Race is invalid!")
            return false
        }
        if (weight.get()?.trim().isNullOrEmpty() || weight.get()?.toDoubleOrNull() == null) {
            showAlert?.invoke("Weight is invalid!")
            return false
        }
        if (age.get()?.trim().isNullOrEmpty() || age.get()?.toIntOrNull() == null) {
            showAlert?.invoke("Age is invalid!")
            return false
        }

//        if (groupId.isNullOrEmpty()) {
//            showAlert?.invoke("An error has occurred!")
//            return false
//        }

        return true
    }

    fun onAdd() {
        if (fieldsAreValid()) {
            val addPetModel = AddPetModel(
                name = name.get() ?: "",
                species = species.get() ?: "",
                race = race.get() ?: "",
                weight = weight.get()?.toDouble() ?: 0.toDouble(),
                age = age.get()?.toInt() ?: 0,
                groupId = groupId ?: ""
            )
            viewModelScope.launch {
                petRepository.addPet(addPetModel)?.let {
                    showAlert?.invoke("Pet added successfully!")
                } ?: run {
                    showAlert?.invoke("An error has occurred!")
                }
            }
            navigateBack?.invoke()
        }
    }

    fun onCancel() {
        navigateBack?.invoke()
    }
}