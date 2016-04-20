package rus.voiceassistant

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.widget.Toast
import java.util.*

/**
 * Created by RUS on 18.04.2016.
 */
fun FragmentManager.addFragment(containerViewId: Int, fragment: Fragment) {
    this.beginTransaction().add(containerViewId, fragment).commit()
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}
fun Calendar.isDateComesToday(hour: Int, minute: Int): Boolean {
    val currentHour = get(Calendar.HOUR_OF_DAY)
    val currentMinute = get(Calendar.MINUTE)

    if(hour == currentHour)
        return minute > currentMinute
    else
        return hour > currentHour
}