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
    set(value) {
        field = value
        this.dateTime = this.dateTime?.withMinuteOfHour(value)
    }

    @DatabaseField
    var hour: Int = 0
    set(value) {
        field = value
        this.dateTime = this.dateTime?.withHourOfDay(value)
    }

    @DatabaseField
    var day: Int = 0
    set(value) {
        field = value
        this.dateTime = this.dateTime?.withDayOfMonth(value)
    }

    @DatabaseField
    var month: Int = 0
    set(value) {
        field = value
        this.dateTime = this.dateTime?.withMonthOfYear(value)
    }

    @DatabaseField
    var year: Int = 0
    set(value) {
        field = year
        this.dateTime = this.dateTime?.withYear(value)
    }

    @DatabaseField
    var text: String = ""

    var dateTime: DateTime? = null

    constructor() {}

    fun getTimeString(): String {
        if(dateTime == null) {
            dateTime = DateTime(year, month, day, hour, minute)
        }
        return "${dateTime?.toLocalTime()?.toString("HH:mm")}"
    }

    fun getDateString(): String {
        if(dateTime == null) {
            dateTime = DateTime(year, month, day, hour, minute)
        }
        return "${dateTime?.toLocalDate()?.toString("dd.MM.yyyy")}"
    }
}