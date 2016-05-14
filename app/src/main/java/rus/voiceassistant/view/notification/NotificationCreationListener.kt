package rus.voiceassistant.view.notification

import rus.voiceassistant.model.actions.Notification

/**
 * Created by RUS on 30.04.2016.
 */
interface NotificationCreationListener {

    fun onNotificationCreated(notification: Notification)

    fun onNotificationEdited(notification: Notification)

}