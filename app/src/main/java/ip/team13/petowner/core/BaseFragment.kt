package ip.team13.petowner.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import ip.team13.petowner.BR

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    abstract val layout: Int

    abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }
}