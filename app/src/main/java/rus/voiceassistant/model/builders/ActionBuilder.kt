package rus.voiceassistant.model.builders

import org.joda.time.DateTime
import rus.voiceassistant.model.actions.Action

/**
 * Created by RUS on 14.05.2016.
 */
abstract class ActionBuilder {
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

    fun second(second: Int?): ActionBuilder {
        this.dateTime = this.dateTime.withSecondOfMinute(second ?: 0)
        return this
    }

    fun secondForward(second: Int?): ActionBuilder {
        this.dateTime = this.dateTime.plusSeconds(second ?: 0)
        return this
    }

    fun minute(minute: Int?): ActionBuilder {
        this.dateTime = this.dateTime.withMinuteOfHour(minute ?: 0)
        return this
    }

    fun minuteForward(minuteForward: Int?): ActionBuilder {
        if(minuteForward != null)
            this.dateTime = this.dateTime.plusMinutes(minuteForward)
        return this
    }

    fun hour(hour: Int?): ActionBuilder {
        this.dateTime = this.dateTime.withHourOfDay(hour ?: 0)
        return this
    }

    fun hourForward(hourForward: Int?): ActionBuilder {
        if(hourForward != null)
            this.dateTime = this.dateTime.plusHours(hourForward)
        return this
    }

    fun day(day: Int?): ActionBuilder {
        if(day != null)
            this.dateTime = this.dateTime.withDayOfMonth(day)
        return this
    }

    fun dayForward(dayForward: Int?): ActionBuilder {
        if(dayForward != null)
            this.dateTime = this.dateTime.plusDays(dayForward)
        return this
    }

    fun month(month: Int?): ActionBuilder {
        if(month != null)
            this.dateTime = this.dateTime.withMonthOfYear(month)
        return this
    }

    fun monthForward(monthForward: Int?): ActionBuilder {
        if(monthForward != null)
            this.dateTime = this.dateTime.plusMonths(monthForward)
        return this
    }

    fun year(year: Int?): ActionBuilder {
        if(year != null)
            this.dateTime = this.dateTime.withYear(year)
        return this
    }

    fun yearForward(yearForward: Int?): ActionBuilder {
        if(yearForward != null)
            this.dateTime = this.dateTime.plusYears(yearForward)
        return this
    }

    abstract fun build(): Action
}