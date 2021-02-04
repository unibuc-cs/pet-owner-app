package ip.team13.petowner

import android.os.Build
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import ip.team13.petowner.ui.cost.CostTrackerFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@Config(manifest = Config.NONE, sdk = [ Build.VERSION_CODES.P])
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("ip.team13.petowner", appContext.packageName)
    }

    @Test
    fun launchFragmentAndVerifyUI() {
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // use launchInContainer to launch the fragment with UI
        launchFragmentInContainer<CostTrackerFragment>()

        // now use espresso to look for the fragment's text view and verify it is displayed
        onView(withId(R.id.pieChart))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}