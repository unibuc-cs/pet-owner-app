package ip.team13.petowner

import androidx.core.content.ContextCompat
import androidx.test.platform.app.InstrumentationRegistry
import ip.team13.petowner.data.dto.CostTrackerCategory
import ip.team13.petowner.data.dto.CostTrackerRecyclerViewModel
import ip.team13.petowner.ui.cost.list.ItemCostTrackerViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    @Test
    fun getColorByCategory_Food_returnsColorAppGreen() {
        // Given a ViewModel with a model of cost track item with category type Food
        val viewModel = ItemCostTrackerViewModel(
            costTrackerModel = CostTrackerRecyclerViewModel(
                category = CostTrackerCategory.FOOD.title,
                name = "",
                cost = 0.0
            )
        )

        // When getting color by type
        val color = viewModel.getColorByCategory()

        // Then it should be colorAppGreen
        assertEquals(color, R.color.colorAppGreen)
    }

    @Test
    fun getColorByCategory_Fun_returnsColorAppOrangeBright() {
        // Given a ViewModel with a model of cost track item with category type Fun
        val viewModel = ItemCostTrackerViewModel(
            costTrackerModel = CostTrackerRecyclerViewModel(
                category = CostTrackerCategory.FUN.title,
                name = "",
                cost = 0.0
            )
        )

        // When getting color by type
        val color = viewModel.getColorByCategory()

        // Then it should be colorAppOrangeBright
        assertEquals(color, R.color.colorAppOrangeBright)
    }

    @Test
    fun getColorByCategory_Health_returnsColorAppYellowBright() {
        // Given a ViewModel with a model of cost track item with category type Health
        val viewModel = ItemCostTrackerViewModel(
            costTrackerModel = CostTrackerRecyclerViewModel(
                category = CostTrackerCategory.HEALTH.title,
                name = "",
                cost = 0.0
            )
        )

        // When getting color by type
        val color = viewModel.getColorByCategory()

        // Then it should be colorAppYellowBright
        assertEquals(color, R.color.colorAppYellowBright)
    }

}