package rus.voiceassistant.model.actions

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import rus.voiceassistant.model.builders.AlarmBuilder
import java.io.Serializable

/**
 * Created by RUS on 10.04.2016.
 */
@DatabaseTable(tableName = "alarms")
class Alarm : Action, Serializable {

    @DatabaseField
    var isOn: Boolean = false

    constructor() : super()

    constructor(builder: AlarmBuilder) : super(builder)

    override fun toString(): String {
        return "${super.toString()} \nAlarm"
    }

}