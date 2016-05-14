package rus.voiceassistant.model.actions

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import org.joda.time.DateTime
import rus.voiceassistant.Logger
import rus.voiceassistant.model.builders.NotificationBuilder
import java.io.Serializable

/**
 * Created by RUS on 28.04.2016.
 */
@DatabaseTable(tableName = "notifications")
class Notification : Action, Serializable {

    @DatabaseField
    var text: String = ""

    constructor() : super() {

    }

    constructor(builder: NotificationBuilder) : super(builder) {
        this.text = builder.text
    }

    override fun toString(): String{
        return "${super.toString()} \nNotification(text='$text')"
    }

}