package rus.voiceassistant.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.alarm_fragment.*
import rus.voiceassistant.R
import rus.voiceassistant.view.adapters.NotificationsAdapter
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.presenter.AlarmPresenter
import rus.voiceassistant.presenter.IAlarmPresenter
import rus.voiceassistant.toast
import rus.voiceassistant.view.adapters.AlarmsAdapter
import java.util.*

/**
 * Created by RUS on 10.04.2016.
 */
class AlarmsFragment : Fragment(), IAlarmView {

    val presenter: IAlarmPresenter = AlarmPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.alarm_fragment, container, false)

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
//                presenter.removeAction()
//                recyclerView.adapter.notifyItemRemoved(viewHolder.adapterPosition)
//            }
//        }
//        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
//        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun setActions(alarms: ArrayList<Alarm>) {
        recyclerView.adapter = AlarmsAdapter(this, alarms)
    }

    override fun onActionAdded() = recyclerView.adapter.notifyItemInserted(recyclerView.adapter.itemCount - 1)

    override fun onTimeSet(view: RadialPickerLayout?, hourOfDay: Int, minute: Int, second: Int) {
        presenter.createAlarm(hourOfDay, minute)
    }

    override fun onAlarmOnOff(alarm: Alarm) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_HOUR, alarm.hour)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, alarm.minute)
        intent.putExtra(AlarmClock.EXTRA_IS_PM, true)
        startActivity(intent)
    }

    override fun onActionRemoved(position: Int) {
    }

    override fun showDeleteActionDialog(position: Int) {
    }

    override fun onCheckedChange(position: Int, isChecked: Boolean) {
        presenter.onCheckedChanged(position, isChecked)
    }

    override fun onDataSetChanged() {
        recyclerView.getAdapter().notifyDataSetChanged()
    }

    override fun getContext(): Context? = activity

    override fun showSnackBar(text: String) {
        toast(text)
    }

    override fun showTimePicker() {
        val tpd: TimePickerDialog = TimePickerDialog.newInstance(this, 0, 0, true);
        tpd.show(activity.fragmentManager, "TimePickerDialog");
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}