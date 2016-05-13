package rus.voiceassistant

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast
import rus.voiceassistant.model.Notification
import rus.voiceassistant.model.yandex.Token
import rus.voiceassistant.receivers.NotificationReceiver
import java.security.AccessControlContext
import java.util.*

/**
 * Created by RUS on 18.04.2016.
 */
fun FragmentManager.addFragment(fragment: Fragment) {
    this.beginTransaction().add(R.id.fragment_container, fragment).commit()
}

fun FragmentManager.replaceFragment(fragment: Fragment) {
    this.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Calendar.isDateComesToday(hour: Int, minute: Int): Boolean {
    val currentHour = get(Calendar.HOUR_OF_DAY)
    val currentMinute = get(Calendar.MINUTE)

    if(hour == currentHour)
        return minute > currentMinute
    else
        return hour > currentHour
}

fun Boolean.toInt(): Int {
    return if(this) 1 else 0
}

fun List<Token>.containsWords(words: Array<String>): Boolean {
    for(word in words) {
        if(filter({ it.text.equals(word) }).isEmpty())
            return false
    }
    return true
}

fun List<Token>.containsAtLeastOneWordFromArray(words: Array<String>): Boolean {
    for(word in words) {
        if(!filter({ it.text.equals(word) }).isEmpty())
            return true
    }
    return false
}

fun List<Token>.containsArraysWords(arrays: Array<Array<String>>): Boolean {
    for(array in arrays) {
        if(!containsAtLeastOneWordFromArray(array))
            return false
    }
    return true
}

fun AlarmManager.createNotification(context: Context, notification: Notification) {
    val notificationIntent = Intent(context, NotificationReceiver::class.java)
    notificationIntent.putExtra("TEXT", notification.text)
    notificationIntent.putExtra("ID", notification.id)
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
    set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
}