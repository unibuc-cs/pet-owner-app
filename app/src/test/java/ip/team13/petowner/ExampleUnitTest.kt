package ip.team13.petowner

import ip.team13.petowner.data.dto.CostTrackerCategory
import ip.team13.petowner.data.dto.CostTrackerRecyclerViewModel
import ip.team13.petowner.ui.activities.details.ActivityDetailsViewModel
import ip.team13.petowner.ui.activities.list.RepeatType
import ip.team13.petowner.ui.cost.list.ItemCostTrackerViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
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

    @Test
    fun getRecurringInterval_value_returnsRepeatTypeValue() {
        val viewModel = ActivityDetailsViewModel(0)

        val value = viewModel.getRecurringInterval(RepeatType.NEVER.title)
        assertEquals(0, value)

        val value2 = viewModel.getRecurringInterval(RepeatType.DAILY.title)
        assertEquals(1, value2)

        val value3 = viewModel.getRecurringInterval(RepeatType.WEEKLY.title)
        assertEquals(7, value3)

        val value4 = viewModel.getRecurringInterval(RepeatType.MONTHLY.title)
        assertEquals(30, value4)
    }

    @Test
    fun getExpPoints_value_returnsRepeatTypeExp() {
        val viewModel = ActivityDetailsViewModel(0)

        val value = viewModel.getExpPoints(RepeatType.NEVER.title)
        assertEquals(0, value)

        val value2 = viewModel.getExpPoints(RepeatType.DAILY.title)
        assertEquals(15, value2)

        val value3 = viewModel.getExpPoints(RepeatType.WEEKLY.title)
        assertEquals(20, value3)

        val value4 = viewModel.getExpPoints(RepeatType.MONTHLY.title)
        assertEquals(50, value4)
    }

    @Test
    fun isValidEmail_email_returnsBoolean() {
//        assertEquals(false, "test@email".isValidEmail())
//        assertEquals(true, "test@email.com".isValidEmail())
    }
}