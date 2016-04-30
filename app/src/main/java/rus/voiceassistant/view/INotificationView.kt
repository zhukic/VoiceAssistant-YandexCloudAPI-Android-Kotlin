package rus.voiceassistant.view

import rus.voiceassistant.model.Notification

/**
 * Created by RUS on 28.04.2016.
 */
interface INotificationView : IView<Notification> {

    fun createNotification(notification: Notification)

    fun showCreateNotificationDialog()

}