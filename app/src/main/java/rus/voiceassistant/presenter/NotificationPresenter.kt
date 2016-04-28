package rus.voiceassistant.presenter

import rus.voiceassistant.database.DatabaseManager
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.model.Notification
import rus.voiceassistant.view.INotificationView
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
class NotificationPresenter(var view: INotificationView?) : INotificationPresenter {

    val notifications: ArrayList<Notification> = ArrayList<Notification>()

    override fun onResume() {
        view?.setActions(notifications)
    }

    override fun removeAction(position: Int) {
        notifications.removeAt(position)
    }

    override fun onAddActionClicked() {
        notifications.add(Notification("23:50", "BLA"))
        view?.onActionAdded(Notification("23:50", "BLA"))
    }

    override fun onDestroy() {
        this.view = null
    }

}