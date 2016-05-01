package rus.voiceassistant.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable

/**
 * Created by RUS on 28.04.2016.
 */
@DatabaseTable(tableName = "notifications")
class Notification : Serializable {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var minute: Int = 0

    @DatabaseField
    var hour: Int = 0

    @DatabaseField
    var day: Int = 0

    @DatabaseField
    var month: Int = 0

    @DatabaseField
    var year: Int = 0

    @DatabaseField
    var text: String = ""

    constructor() {}

    fun getTimeString(): String {
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

    fun getDateString(): String {
        var stringDate = ""
        if (day < 10)
            stringDate += "0$day"
        else
            stringDate += "$day"

        stringDate += "."

        if (month < 10)
            stringDate += "0$month"
        else stringDate += "$month"

        stringDate += ".$year"

        return stringDate
    }
}