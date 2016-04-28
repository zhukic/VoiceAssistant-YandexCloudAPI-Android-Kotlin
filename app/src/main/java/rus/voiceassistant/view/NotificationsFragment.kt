package rus.voiceassistant.view;

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.alarm_fragment.*
import rus.voiceassistant.R
import rus.voiceassistant.model.Notification
import rus.voiceassistant.presenter.AlarmPresenter
import rus.voiceassistant.presenter.IAlarmPresenter
import rus.voiceassistant.presenter.INotificationPresenter
import rus.voiceassistant.presenter.NotificationPresenter
import rus.voiceassistant.toast
import rus.voiceassistant.view.adapters.NotificationsAdapter
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
class NotificationsFragment : Fragment(), INotificationView {
    val presenter: INotificationPresenter = NotificationPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.notification_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        fab.setOnClickListener { presenter.onAddActionClicked() }

        recyclerView.layoutManager = LinearLayoutManager(getActivity())

//        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0,
        //                ItemTouchHelper.LEFT or  ItemTouchHelper.RIGHT) {
        //
        //            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        //                return true
        //            }
        //
        //            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        //                presenter.removeAction(viewHolder!!.adapterPosition)
        //                recyclerView.adapter.notifyItemRemoved(viewHolder!!.adapterPosition)
        //            }
        //        }
        //        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        //        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun showSnackbar(text: String) {
        toast(text)
    }

    override fun onActionAdded(action: Notification) = recyclerView.adapter.notifyItemInserted(recyclerView.adapter.itemCount - 1)

    override fun setActions(actions: ArrayList<Notification>) {
        recyclerView.adapter = NotificationsAdapter(this, actions);
    }

    override fun onActionClicked(action: Notification) {

    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
