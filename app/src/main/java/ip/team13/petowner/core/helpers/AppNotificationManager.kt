package ip.team13.petowner.core.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import ip.team13.petowner.R

object AppNotificationManager {

    private const val NOTIFICATION_CHANNEL_ID = "PetOwner"
    private const val NOTIFICATION_CHANNEL_NAME = "PetOwner"

    fun buildNotification(
        context: Context,
        title: String,
        body: String
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_group)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(title)
            .setContentText(body)
            .priority = NotificationCompat.PRIORITY_HIGH

        notificationManager.notify(1, notificationBuilder.build())
    }
}