package rus.voiceassistant.model

import com.j256.ormlite.field.DatabaseField
import org.joda.time.DateTime

/**
 * Created by RUS on 09.05.2016.
 */
open class Action(@DatabaseField var year: Int = 0,
                  @DatabaseField var month: Int = 0,
                  @DatabaseField var day: Int = 0,
                  @DatabaseField var hour: Int = 0,
                  @DatabaseField var minute: Int = 0,
                  @DatabaseField var second: Int = 0) {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var isDone: Boolean = false

    var originalRequest: String = ""

    abstract class Builder {
        var dateTime: DateTime

        constructor() {
            dateTime = DateTime()
        }

        constructor(action: Action) {
            dateTime = DateTime()
            this.dateTime = this.dateTime.withYear(action.year)
            this.dateTime = this.dateTime.withMonthOfYear(action.month)
            this.dateTime = this.dateTime.withDayOfMonth(action.day)
            this.dateTime = this.dateTime.withHourOfDay(action.hour)
            this.dateTime = this.dateTime.withMinuteOfHour(action.minute)
            this.dateTime = this.dateTime.withSecondOfMinute(action.second)
        }

        fun second(second: Int?): Builder {
            if(second != null)
                this.dateTime = this.dateTime.withSecondOfMinute(second)
            return this
        }

        fun secondForward(second: Int?): Builder {
            this.dateTime = this.dateTime.plusSeconds(second ?: 0)
            return this
        }

        fun minute(minute: Int?): Builder {
            if(minute != null)
                this.dateTime = this.dateTime.withMinuteOfHour(minute)
            return this
        }

        fun minuteForward(minuteForward: Int?): Builder {
            if(minuteForward != null)
                this.dateTime = this.dateTime.plusMinutes(minuteForward)
            return this
        }

        fun hour(hour: Int?): Builder {
            if(hour != null)
                this.dateTime = this.dateTime.withHourOfDay(hour)
            return this
        }

        fun hourForward(hourForward: Int?): Builder {
            if(hourForward != null)
                this.dateTime = this.dateTime.plusHours(hourForward)
            return this
        }

        fun day(day: Int?): Builder {
            if(day != null)
                this.dateTime = this.dateTime.withDayOfMonth(day)
            return this
        }

        fun dayForward(dayForward: Int?): Builder {
            if(dayForward != null)
                this.dateTime = this.dateTime.plusDays(dayForward)
            return this
        }

        fun month(month: Int?): Builder {
            if(month != null)
                this.dateTime = this.dateTime.withMonthOfYear(month)
            return this
        }

        fun monthForward(monthForward: Int?): Builder {
            if(monthForward != null)
                this.dateTime = this.dateTime.plusMonths(monthForward)
            return this
        }

        fun year(year: Int?): Builder {
            if(year != null)
                this.dateTime = this.dateTime.withYear(year)
            return this
        }

        fun yearForward(yearForward: Int?): Builder {
            if(yearForward != null)
                this.dateTime = this.dateTime.plusYears(yearForward)
            return this
        }

        abstract fun build(): Action
    }

    constructor(builder: Builder) : this(builder.dateTime.year, builder.dateTime.monthOfYear - 1, builder.dateTime.dayOfMonth, builder.dateTime.hourOfDay, builder.dateTime.minuteOfHour)

    fun getTimeString(): String {
        val dateTime = DateTime(year, month + 1, day, hour, minute)
        return "${dateTime.toLocalTime()?.toString("HH:mm")}"
    }

    fun getDateString(): String {
        val dateTime = DateTime(year, month + 1, day, hour, minute)
        return "${dateTime.toLocalDate()?.toString("dd.MM.yyyy")}"
    }

    override fun toString(): String{
        return "Action(id=$id, minute=$minute, hour=$hour, day=$day, month=$month, year=$year, originalRequest=$originalRequest)"
    }

}