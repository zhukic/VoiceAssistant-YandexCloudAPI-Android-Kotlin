package rus.voiceassistant

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import rus.voiceassistant.model.Notification
import rus.voiceassistant.receivers.NotificationReceiver
import java.util.*

/**
 * Created by RUS on 12.05.2016.
 */
class ActionManager {

    companion object {

        fun createNotification(context: Context, notification: Notification) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val notificationIntent = Intent(context, NotificationReceiver::class.java)
            notificationIntent.putExtra("TEXT", notification.text)
            notificationIntent.putExtra("ID", notification.id)
            Logger.log(notification.toString())
            val pendingIntent = PendingIntent.getBroadcast(context, notification.id, notificationIntent, PendingIntent.FLAG_ONE_SHOT)
            val calendar = Calendar.getInstance()
            with(calendar) {
                setTimeInMillis(System.currentTimeMillis())
                set(Calendar.YEAR, notification.year)
                set(Calendar.MONTH, notification.month)
                set(Calendar.DAY_OF_MONTH, notification.day)
                set(Calendar.HOUR_OF_DAY, notification.hour)
                set(Calendar.MINUTE, notification.minute)
                set(Calendar.SECOND, 0)
            }
            alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
        }

        fun cancelNotification(context: Context, notification: Notification) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val notificationIntent = Intent(context, NotificationReceiver::class.java)
            notificationIntent.putExtra("TEXT", notification.text)
            notificationIntent.putExtra("ID", notification.id)
            val pendingIntent = PendingIntent.getBroadcast(context, notification.id, notificationIntent, PendingIntent.FLAG_ONE_SHOT)
            alarmManager.cancel(pendingIntent)
        }
    }

}