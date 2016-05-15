package rus.voiceassistant

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock
import rus.voiceassistant.model.actions.Alarm
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.receivers.NotificationReceiver
import java.util.*

/**
 * Created by RUS on 12.05.2016.
 */
class ActionCreator {

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

        fun createAlarm(context: Context, alarm: Alarm) {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_HOUR, alarm.hour)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, alarm.minute)
            intent.putExtra(AlarmClock.EXTRA_IS_PM, true)
            context.startActivity(intent)
        }

        fun openBrowser(context: Context, text: String) {
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.google.ru/search?q=$text"))
            context.startActivity(intent)
        }
    }

}