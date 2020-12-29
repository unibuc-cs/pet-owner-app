package ip.team13.petowner.core.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ip.team13.petowner.core.persistence.saveFcmToken
import ip.team13.petowner.core.helpers.AppNotificationManager

class AppFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            buildNotification(notification = it)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        this.saveFcmToken(token)
    }

    private fun buildNotification(notification: RemoteMessage.Notification) {
        AppNotificationManager.buildNotification(
            context = this,
            title = notification.title ?: return,
            body = notification.body ?: return
        )
    }
}