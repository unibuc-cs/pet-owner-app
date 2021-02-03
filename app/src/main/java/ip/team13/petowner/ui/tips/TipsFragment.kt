package ip.team13.petowner.ui.tips

import android.os.Bundle
import android.view.View
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.databinding.TipsScreenBinding
import ip.team13.petowner.ui.tips.list.TipsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TipsFragment : BaseFragment<TipsScreenBinding>() {

    override val layout: Int = R.layout.tips_screen
    override val viewModel: TipsViewModel by viewModel()

    private lateinit var adapter: TipsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TipsAdapter(arrayListOf())
        binding.adapter = adapter

    }

}