package rus.voiceassistant.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable

/**
 * Created by RUS on 10.04.2016.
 */
@DatabaseTable(tableName = "alarms")
class Alarm : Action, Serializable {

    @DatabaseField
    var isOn: Boolean = false

    class Builder : Action.Builder {

        constructor() : super()

        constructor(alarm: Alarm) : super(alarm)

        override fun build(): Action = Alarm(this)
    }

    constructor() : super() {

    }

    constructor(builder: Alarm.Builder) : super(builder)

    override fun toString(): String {
        return "${super.toString()} \nAlarm"
    }

}