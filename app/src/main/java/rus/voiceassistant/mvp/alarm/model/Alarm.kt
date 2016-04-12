package rus.voiceassistant.mvp.alarm.model

/**
 * Created by RUS on 10.04.2016.
 */
class Alarm(var hour: Int = 0, var minute: Int = 0) {

    fun getTime(): String {
        var stringTime = ""
        if (hour < 10)
            stringTime += "0" + hour.toString()
        else
            stringTime += hour.toString()

        stringTime += ":"

        if (minute < 10)
            stringTime += "0" + minute.toString()
        else stringTime += minute.toString()

        return stringTime
    }

}