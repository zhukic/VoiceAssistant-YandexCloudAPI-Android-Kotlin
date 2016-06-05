package rus.voiceassistant.presenter.notification

import rus.voiceassistant.ActionCreator
import rus.voiceassistant.MyApplication
import rus.voiceassistant.database.DatabaseManager
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.presenter.ItemPresenter
import rus.voiceassistant.view.ItemView
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
class NotificationPresenter(var view: ItemView<Notification>?) : ItemPresenter<Notification> {

    val notifications: ArrayList<Notification> = DatabaseManager.getNotificationsListFromDatabase()

    override fun onResume() {
        notifications.sort()
        view?.setActions(notifications)
    }

    override fun onAddActionClicked() {
        view?.showItemFragmentDialog()
    }

    override fun onActionClicked(position: Int) {
        view?.showItemFragmentDialog(notifications[position])
    }

    override fun onLongActionClicked(position: Int) {
        view?.showDeleteItemFragmentDialog(position)
    }

    override fun onItemCreated(item: Notification) {
        notifications.add(item)
        notifications.sort()
        MyApplication.notificationDao.create(item)
        view?.onDataSetChanged()
        ActionCreator.createNotification(view!!.getContext(), item)
    }

    override fun onItemEdited(item: Notification) {
        MyApplication.notificationDao.update(item)
        ActionCreator.createNotification(view!!.getContext(), item)
        view?.onDataSetChanged()
    }

    override fun removeAction(position: Int) {
        if(!notifications[position].isDone)
            ActionCreator.cancelNotification(view!!.getContext(), notifications[position])
        MyApplication.notificationDao.deleteById(notifications[position].id)
        notifications.removeAt(position)
        view?.onDataSetChanged()
    }

    override fun onDestroy() {
        this.view = null
    }

}