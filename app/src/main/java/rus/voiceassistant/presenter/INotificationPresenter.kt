package rus.voiceassistant.presenter

import rus.voiceassistant.model.Notification

/**
 * Created by RUS on 28.04.2016.
 */
interface INotificationPresenter : IPresenter<Notification> {

    fun onNotificationCreated(notification: Notification)

    fun onNotificationEdited(notification: Notification)
}