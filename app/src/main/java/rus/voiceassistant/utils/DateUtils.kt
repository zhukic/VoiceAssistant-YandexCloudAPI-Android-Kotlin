package rus.voiceassistant.utils

import android.util.Log
import java.util.*

/**
 * Created by RUS on 16.04.2016.
 */
class DateUtils {

    companion object {

        fun isDateComesToday(hour: Int, minute: Int): Boolean {
            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            val currentMinute = calendar.get(Calendar.MINUTE)

            if(hour == currentHour)
                return minute > currentMinute
            else
                return hour > currentHour
        }

    }

}