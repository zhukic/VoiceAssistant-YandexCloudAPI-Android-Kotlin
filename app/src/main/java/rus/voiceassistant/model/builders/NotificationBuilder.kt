package rus.voiceassistant.model.builders

import rus.voiceassistant.model.actions.Action
import rus.voiceassistant.model.actions.Notification

/**
 * Created by RUS on 14.05.2016.
 */
class NotificationBuilder : ActionBuilder {
    var text: String = ""

    constructor() : super()

    constructor(notification: Notification) : super(notification) {
        this.text = notification.text
    }

    fun text(text: String?): NotificationBuilder {
        this.text = text ?: ""
        return this
    }

    override fun build(): Action = Notification(this)
}