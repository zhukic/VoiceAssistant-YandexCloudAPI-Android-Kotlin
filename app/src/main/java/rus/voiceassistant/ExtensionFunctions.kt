package rus.voiceassistant

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast
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

fun Calendar.isDateComesToday(hour: Int, minute: Int): Boolean {
    val currentHour = get(Calendar.HOUR_OF_DAY)
    val currentMinute = get(Calendar.MINUTE)

    if(hour == currentHour)
        return minute > currentMinute
    else
        return hour > currentHour
}

fun Log.log(message: String) {
    Log.i("TAG", message);
}