package rus.voiceassistant.presenter.notification

import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.presenter.IPresenter

/**
 * Created by RUS on 28.04.2016.
 */
interface INotificationPresenter : IPresenter<Notification> {

    fun onNotificationCreated(notification: Notification)

    fun onNotificationEdited(notification: Notification)
}