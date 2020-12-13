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

    protected lateinit var dataBinding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layout, container, false)
        dataBinding.setVariable(BR.viewModel, viewModel)

        return dataBinding.root
    }
}