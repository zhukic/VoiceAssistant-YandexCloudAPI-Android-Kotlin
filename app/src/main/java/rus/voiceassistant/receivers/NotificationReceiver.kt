package rus.voiceassistant.receivers

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.support.v7.app.NotificationCompat
import rus.voiceassistant.Logger
import rus.voiceassistant.R
import rus.voiceassistant.MainActivity
import rus.voiceassistant.MyApplication

/**
 * Created by RUS on 29.04.2016.
 */
class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Logger.log("onReceive ${intent?.extras?.getInt("ID")}")
        val notificationBuilder = NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Напоминание")
                .setContentText(intent?.extras?.getString("TEXT"))
                .setLights(R.color.colorPrimary, 1000, 1000)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
        val  mNotificationId = intent?.extras?.getInt("ID") as Int
        val  mNotifyMgr = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
        mNotifyMgr.notify(mNotificationId, notificationBuilder.build());
        val notification = MyApplication.notificationDao.queryForId(mNotificationId)
        notification.isDone = true
        MyApplication.notificationDao.update(notification)
    }
}