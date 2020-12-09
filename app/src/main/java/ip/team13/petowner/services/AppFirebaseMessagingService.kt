package ip.team13.petowner.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ip.team13.petowner.helpers.saveFcmToken
import ip.team13.petowner.utils.AppNotificationManager

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