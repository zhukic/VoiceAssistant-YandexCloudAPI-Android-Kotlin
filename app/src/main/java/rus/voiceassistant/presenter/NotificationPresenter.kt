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
        view?.hideFAB()
        view?.showEditText()
//        val notification = Notification()
//        notification.time = "23:50"
//        notification.text = "BLA"
//        notifications.add(notification)
//        MyApplication.notificationDao.create(notification)
//        view?.onActionAdded(notification)
//        view?.createNotification(notification)
    }

    override fun removeAction(position: Int) {
        DatabaseManager.removeNotification(notifications[position].id)
        notifications.removeAt(position)
    }

    override fun onDestroy() {
        this.view = null
    }

}