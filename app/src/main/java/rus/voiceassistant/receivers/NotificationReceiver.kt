package rus.voiceassistant.receivers

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.support.v7.app.NotificationCompat
import rus.voiceassistant.*

/**
 * Created by RUS on 29.04.2016.
 */
class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Logger.log("onReceive ${intent?.extras?.getInt("ID")}")
        val n = notification(context!!) {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("Напоминание")
            setContentText(intent?.extras?.getString("TEXT"))
            setLights(R.color.colorPrimary, 1000, 1000)
            setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
        }
        val notificationId = intent?.extras?.getInt("ID") as Int
        val notifyMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
        notifyMgr.notify(notificationId, n);
        val notification = MyApplication.notificationDao.queryForId(notificationId)
        notification.isDone = true
        MyApplication.notificationDao.update(notification)
    }
}