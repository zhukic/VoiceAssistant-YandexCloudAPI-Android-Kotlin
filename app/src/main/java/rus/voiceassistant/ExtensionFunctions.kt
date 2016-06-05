package rus.voiceassistant

import android.app.Activity
import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast
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

inline fun notification(context: Context, func: Notification.Builder.() -> Unit ): Notification {
    val builder = Notification.Builder(context)
    builder.func()
    return builder.build()
}