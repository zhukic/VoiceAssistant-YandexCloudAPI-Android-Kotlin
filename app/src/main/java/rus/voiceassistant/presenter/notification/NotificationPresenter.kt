package rus.voiceassistant.presenter.notification

import android.support.v7.app.NotificationCompat
import rus.voiceassistant.Logger
import rus.voiceassistant.MyApplication
import rus.voiceassistant.database.DatabaseManager
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.model.Notification
import rus.voiceassistant.view.notification.INotificationView
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
class NotificationPresenter(var view: INotificationView?) : INotificationPresenter {

    val notifications: ArrayList<Notification> = DatabaseManager.getNotificationsListFromDatabase()

    override fun onResume() {
        notifications.sort()
        view?.setActions(notifications)
    }

    override fun onAddActionClicked() {
        view?.showNotificationDialog()
    }

    override fun onActionClicked(position: Int) {
        Logger.log("onActionClicked=$position")
        view?.showNotificationDialog(notifications[position])
    }

    override fun onLongActionClicked(position: Int) {
        Logger.log("onLongActionClicked=$position")
        view?.showDeleteActionDialog(position)
    }

    override fun onNotificationCreated(notification: Notification) {
        notifications.add(notification)
        notifications.sort()
        MyApplication.notificationDao.create(notification)
        view?.onDataSetChanged()
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
        view?.onDataSetChanged()
    }

    override fun onDestroy() {
        this.view = null
    }

}