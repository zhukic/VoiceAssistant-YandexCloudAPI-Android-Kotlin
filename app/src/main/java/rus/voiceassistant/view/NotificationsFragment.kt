package rus.voiceassistant.view;

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v7.app.NotificationCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.notification_fragment.*
import rus.voiceassistant.Logger
import rus.voiceassistant.R
import rus.voiceassistant.main.view.MainActivity
import rus.voiceassistant.model.Notification
import rus.voiceassistant.presenter.AlarmPresenter
import rus.voiceassistant.presenter.IAlarmPresenter
import rus.voiceassistant.presenter.INotificationPresenter
import rus.voiceassistant.presenter.NotificationPresenter
import rus.voiceassistant.receivers.NotificationReceiver
import rus.voiceassistant.toast
import rus.voiceassistant.view.adapters.NotificationsAdapter
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
class NotificationsFragment : Fragment(), INotificationView, NotificationCreationListener {

    val presenter: INotificationPresenter = NotificationPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.notification_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        fab.setOnClickListener { presenter.onAddActionClicked() }

        recyclerView.layoutManager = LinearLayoutManager(getActivity())

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or  ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                presenter.removeAction(viewHolder!!.adapterPosition)
                recyclerView.adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun createNotification(notification: Notification) {
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(activity, NotificationReceiver::class.java)
        notificationIntent.putExtra("TEXT", notification.text)
        notificationIntent.putExtra("ID", notification.id)
        Logger.log(notification.id.toString())
        val pendingIntent = PendingIntent.getBroadcast(activity, notification.id, notificationIntent, PendingIntent.FLAG_ONE_SHOT)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, 10)
        alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent);
    }

    override fun onActionAdded(action: Notification) = recyclerView.adapter.notifyItemInserted(recyclerView.adapter.itemCount - 1)

    override fun setActions(actions: ArrayList<Notification>) {
        recyclerView.adapter = NotificationsAdapter(this, actions);
    }

    override fun onActionClicked(action: Notification) {

    }

    override fun showCreateNotificationDialog() {
        val fragmentManager = (activity as MainActivity).supportFragmentManager
        val createNotificationDialog = CreateNotificationFragmentDialog.Companion.newInstance();

        createNotificationDialog.setTargetFragment(this, 300)
        createNotificationDialog.show(fragmentManager, "fragment_create_notification");
    }

    override fun onNotificationCreated(notification: Notification) {
        presenter.onNotificationCreated(notification)
    }

    override fun showSnackbar(text: String) {
        toast(text)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
