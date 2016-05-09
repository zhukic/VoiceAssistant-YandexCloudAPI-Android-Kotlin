package rus.voiceassistant.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import org.joda.time.DateTime
import rus.voiceassistant.Logger
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
        val dateTime = DateTime(year, month, day, hour, minute)
        return "${dateTime.toLocalTime()?.toString("HH:mm")}"
    }

    fun getDateString(): String {
        val dateTime = DateTime(year, month, day, hour, minute)
        return "${dateTime.toLocalDate()?.toString("dd.MM.yyyy")}"
    }
}