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
import java.text.FieldPosition
import java.util.*

/**
 * Created by RUS on 18.04.2016.
 */
fun Boolean.toInt(): Int {
    return if(this) 1 else 0
}

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

private fun List<Token>.containsAtLeastOneWordFromArrayOnPosition(words: Array<String>, position: Int): Boolean {
    for(word in words) {
        if(this[position].text.equals(word))
            return true
    }
    return false
}

fun List<Token>.startsWithAtLeastOneWordFromArray(words: Array<String>): Boolean {
    return containsAtLeastOneWordFromArrayOnPosition(words, 0)
}

fun List<Token>.startsWithArraysWords(arrays: Array<Array<String>>): Boolean {
    if(size < arrays.size)
        return false
    for(i in 0..arrays.size - 1) {
        if(!containsAtLeastOneWordFromArrayOnPosition(arrays[i], i))
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