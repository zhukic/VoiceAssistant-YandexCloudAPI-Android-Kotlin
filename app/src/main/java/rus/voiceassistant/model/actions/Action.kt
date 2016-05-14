package rus.voiceassistant.model.actions

import com.j256.ormlite.field.DatabaseField
import org.joda.time.DateTime
import rus.voiceassistant.model.builders.ActionBuilder

/**
 * Created by RUS on 09.05.2016.
 */
open class Action(@DatabaseField var year: Int = 0,
                  @DatabaseField var month: Int = 0,
                  @DatabaseField var day: Int = 0,
                  @DatabaseField var hour: Int = 0,
                  @DatabaseField var minute: Int = 0,
                  @DatabaseField var second: Int = 0) : Comparable<Action> {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var isDone: Boolean = false

    var originalRequest: String = ""



    constructor(builder: ActionBuilder) : this(
            builder.dateTime.year,
            builder.dateTime.monthOfYear - 1,
            builder.dateTime.dayOfMonth,
            builder.dateTime.hourOfDay,
            builder.dateTime.minuteOfHour,
            builder.dateTime.secondOfMinute)

    fun getDateTime(): DateTime = DateTime(year, month + 1, day, hour, minute, second)

    fun getTimeString(): String {
        val dateTime = DateTime(year, month + 1, day, hour, minute)
        return "${dateTime.toLocalTime()?.toString("HH:mm")}"
    }

    fun getDateString(): String {
        val dateTime = DateTime(year, month + 1, day, hour, minute)
        return "${dateTime.toLocalDate()?.toString("dd.MM.yyyy")}"
    }

    override fun compareTo(other: Action): Int {
        if(isDone.equals(other.isDone))
            return getDateTime().compareTo(other.getDateTime())
        else return isDone.compareTo(other.isDone) * (-1)
    }

    override fun toString(): String{
        return "Action(id=$id, minute=$minute, hour=$hour, day=$day, month=$month, year=$year, originalRequest=$originalRequest)"
    }

}