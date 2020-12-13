package ip.team13.petowner.core.helpers

import androidx.databinding.Observable

fun <T: Observable> T.addOnPropertyChanged(callback: (T) -> Unit) =
    addOnPropertyChangedCallback(
        object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(
                observable: Observable?, i: Int) =
                callback(observable as T)
        })