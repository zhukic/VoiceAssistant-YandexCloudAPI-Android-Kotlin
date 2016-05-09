package rus.voiceassistant.view.notification

import rus.voiceassistant.model.Notification
import rus.voiceassistant.view.IView

/**
 * Created by RUS on 28.04.2016.
 */
interface INotificationView : IView<Notification> {

    fun createNotification(notification: Notification)

    fun cancelNotification(notification: Notification)

    fun showNotificationDialog(notification: Notification? = null)

}