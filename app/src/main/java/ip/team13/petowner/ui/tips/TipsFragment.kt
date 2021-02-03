package ip.team13.petowner.ui.tips

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ip.team13.petowner.R
import ip.team13.petowner.core.BaseFragment
import ip.team13.petowner.core.helpers.showAlertDialog
import ip.team13.petowner.databinding.TipsScreenBinding
import ip.team13.petowner.ui.tips.list.TipsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TipsFragment : BaseFragment<TipsScreenBinding>() {

    override val layout: Int = R.layout.tips_screen
    override val viewModel: TipsViewModel by viewModel()

    private val args: TipsFragmentArgs by navArgs()

    private lateinit var adapter: TipsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!args.isVip) {
            context?.showAlertDialog(
                title = "You are not a VIP user",
                message = "In order to see tips, you need to upgrade to VIP from User Profile.",
                positiveButtonText = "Ok",
                onPositiveAction = {
                    findNavController().popBackStack()
                }
            )
        }

        adapter = TipsAdapter(arrayListOf())
        binding.adapter = adapter

    }

}