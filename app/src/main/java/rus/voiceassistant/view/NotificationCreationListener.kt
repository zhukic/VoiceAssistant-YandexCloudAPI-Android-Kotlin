package rus.voiceassistant.view

import rus.voiceassistant.model.Notification

/**
 * Created by RUS on 30.04.2016.
 */
interface NotificationCreationListener {

    fun onNotificationCreated(notification: Notification)

}