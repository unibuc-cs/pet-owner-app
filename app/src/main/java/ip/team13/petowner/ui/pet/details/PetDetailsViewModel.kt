package ip.team13.petowner.ui.pet.details

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.data.dto.AddPetModel
import ip.team13.petowner.data.repository.PetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

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

    private val photos = arrayListOf(
        "https://ichef.bbci.co.uk/news/1024/cpsprodpb/151AB/production/_111434468_gettyimages-1143489763.jpg",
        "https://scontent.ftsr1-2.fna.fbcdn.net/v/t1.15752-9/144204541_1003936813467889_1222826892948624829_n.jpg?_nc_cat=105&ccb=2&_nc_sid=ae9488&_nc_ohc=313BzYmBcgQAX-tsd12&_nc_ht=scontent.ftsr1-2.fna&oh=5218e1be05445f86de98531c22f430fa&oe=60399884",
        "https://www.sciencenewsforstudents.org/wp-content/uploads/2020/05/1030_LL_domestic_cats.jpg",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/german-shepherd-dog-1557314959.jpg?crop=0.615xw:1.00xh;0.197xw,0&resize=980:*",
        "https://scontent.ftsr1-2.fna.fbcdn.net/v/t1.15752-9/144131633_193999855782836_5278093711675311070_n.jpg?_nc_cat=105&ccb=2&_nc_sid=ae9488&_nc_ohc=fQcmjTlhtCUAX-Pslvl&_nc_ht=scontent.ftsr1-2.fna&oh=c6c34335e763b4db6abed0053a8c93f3&oe=603B3290"
    )

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
                groupId = 0,
                photo = photos.random()
            )
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    petRepository.addPet(addPetModel)
                    withContext(Dispatchers.Main) {
                        showAlert?.invoke("Pet added successfully!")
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        showAlert?.invoke("An error has occurred!")
                    }

                }
            }
        }
    }

    fun onCancel() {
        navigateBack?.invoke()
    }
}