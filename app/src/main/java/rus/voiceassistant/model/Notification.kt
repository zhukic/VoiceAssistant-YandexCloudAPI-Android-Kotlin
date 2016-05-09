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
class Notification : Action, Serializable {

    @DatabaseField
    var text: String = ""

    class Builder : Action.Builder {
        var text: String = ""

        constructor() : super()

        constructor(notification: Notification) : super(notification) {
            this.text = notification.text
        }

        fun text(text: String?): Builder {
            this.text = text ?: ""
            return this
        }

        override fun build(): Action = Notification(this)
    }

    constructor() : super() {

    }

    constructor(builder: Builder) : super(builder) {
        this.text = builder.text
    }

    override fun toString(): String{
        return "${super.toString()} \nNotification(text='$text')"
    }

}