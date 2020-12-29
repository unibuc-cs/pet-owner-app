package ip.team13.petowner.core.injection

import ip.team13.petowner.core.persistence.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { Preferences(androidContext()) }
}