package ip.team13.petowner.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import ip.team13.petowner.R
import ip.team13.petowner.databinding.MainScreenBinding

class MainActivity : FragmentActivity() {

    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<MainScreenBinding>(this, R.layout.main_screen)
        binding.viewModel = viewModel
    }
}