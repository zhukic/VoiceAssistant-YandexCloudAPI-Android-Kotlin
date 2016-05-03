package rus.voiceassistant.presenter

import android.support.v7.app.NotificationCompat
import rus.voiceassistant.MyApplication
import rus.voiceassistant.database.DatabaseManager
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.model.Notification
import rus.voiceassistant.view.INotificationView
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
class NotificationPresenter(var view: INotificationView?) : INotificationPresenter {

    val notifications: ArrayList<Notification> = DatabaseManager.getNotificationsListFromDatabase()

    override fun onResume() {
        view?.setActions(notifications)
    }

    override fun onAddActionClicked() {
        view?.showNotificationDialog()
    }

    override fun onActionClicked(action: Notification) {
        view?.showNotificationDialog(action)
    }

    override fun onNotificationCreated(notification: Notification) {
        notifications.add(notification)
        MyApplication.notificationDao.create(notification)
        view?.onActionAdded(notification)
        view?.createNotification(notification)
    }

    override fun onNotificationEdited(notification: Notification) {
        MyApplication.notificationDao.update(notification)
        view?.createNotification(notification)
        view?.onDataSetChanged()
    }

    override fun removeAction(position: Int) {
        view?.cancelNotification(notifications[position])
        MyApplication.notificationDao.deleteById(notifications[position].id)
        notifications.removeAt(position)
    }

    override fun onDestroy() {
        this.view = null
    }

}