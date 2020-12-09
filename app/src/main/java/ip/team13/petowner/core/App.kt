package ip.team13.petowner.core

import android.app.Application
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import ip.team13.petowner.helpers.logError
import ip.team13.petowner.helpers.saveFcmToken
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initFirebaseMessaging()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            loadKoinModules(appModule)
        }
    }

    private fun initFirebaseMessaging() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            when (task.isSuccessful) {
                true ->
                    task.result?.let { token ->
                        this.saveFcmToken(token = token)
                    } ?: run {
                        "Received FCM token but was NULL".logError()
                    }
                false ->
                    "Fetching FCM registration token failed error: ${task.exception}".logError()
            }
        })
    }
}