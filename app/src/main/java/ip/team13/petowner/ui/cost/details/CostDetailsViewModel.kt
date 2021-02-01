package ip.team13.petowner.ui.cost.details

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ip.team13.petowner.core.helpers.logError
import ip.team13.petowner.data.dto.CostTrackerCategory
import ip.team13.petowner.data.dto.CostTrackerModel
import ip.team13.petowner.data.repository.CostTrackerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CostDetailsViewModel(
    private val repository: CostTrackerRepository
) : ViewModel() {

    var showAlert: ((String) -> Unit)? = null
    var navigateBack: (() -> Unit)? = null

    val name = ObservableField("")
    val category = ObservableField("")
    val cost = ObservableField("")

    fun onCategoryClick(view: View) {
        onShowRepeatDialog(
            context = view.context,
            choices = CostTrackerCategory.values().map { it.title }.toTypedArray(),
            title = "Choose category"
        )
    }

    fun onCancel() {
        navigateBack?.invoke()
    }

    fun onAddClick() {
        val costName = name.get()?.trim()
        val costCategory = category.get()?.trim()
        val costValue = cost.get()

        when {
            costName.isNullOrEmpty() -> {
                showAlert?.invoke("Please add a name.")
                return
            }
            costCategory.isNullOrEmpty() -> {
                showAlert?.invoke("Please select a category.")
                return
            }
            costValue.isNullOrEmpty() -> {
                showAlert?.invoke("Please add the price.")
                return
            }
        }

        doAddCostItem(
            CostTrackerModel(
                name = costName ?: return,
                category = costCategory ?: return,
                cost = costValue?.toDouble() ?: return
            )
        )
    }

    private fun doAddCostItem(costTracker: CostTrackerModel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.addCostItem(costTracker)
            withContext(Dispatchers.Main) {
                showAlert?.invoke("Item added successfully.")
                navigateBack?.invoke()
            }
        } catch (e: Exception) {
            e.message?.logError()
            withContext(Dispatchers.Main) {
                showAlert?.invoke("An error has occurred. Please try again later.")
            }
        }
    }

    private fun onShowRepeatDialog(context: Context, choices: Array<CharSequence>, title: String) {

        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setSingleChoiceItems(
            choices, -1
        ) { dialog, item ->
            category.set(choices[item].toString())
            dialog.dismiss()
        }
        val alert: AlertDialog = alertDialogBuilder.create()
        alert.show()
    }
}