package ip.team13.petowner.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ip.team13.petowner.R
import ip.team13.petowner.databinding.MainScreenBinding

class MainActivity : FragmentActivity() {

    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<MainScreenBinding>(this, R.layout.main_screen)
        binding.viewModel = viewModel

        val navController = findNavController(R.id.nav_host_fragment).apply {
            addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.registerFragment -> binding.bottomNav.visibility = View.GONE
                    else -> binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }

        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)
    }
}