package rus.voiceassistant.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by RUS on 10.04.2016.
 */
@DatabaseTable(tableName = "alarms")
class Alarm {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var hour: Int = 0

    @DatabaseField
    var minute: Int = 0;

    @DatabaseField
    var isOn: Boolean = false

    constructor() {}

    fun getTime(): String {
        var stringTime = ""
        if (hour < 10)
            stringTime += "0$hour"
        else
            stringTime += "$hour"

        stringTime += ":"

        if (minute < 10)
            stringTime += "0$minute"
        else stringTime += "$minute"

        return stringTime
    }

}