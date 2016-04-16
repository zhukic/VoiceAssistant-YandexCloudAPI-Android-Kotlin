package rus.voiceassistant.mvp.alarm.view

import android.app.AlarmManager
import android.app.Fragment
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
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
import rus.voiceassistant.RecyclerAdapter
import rus.voiceassistant.mvp.alarm.model.Alarm
import rus.voiceassistant.mvp.alarm.presenter.AlarmPresenter
import rus.voiceassistant.mvp.alarm.presenter.IAlarmPresenter
import java.util.*

/**
 * Created by RUS on 10.04.2016.
 */
class AlarmsFragment : Fragment(), IAlarmView {

    val presenter: IAlarmPresenter = AlarmPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.alarm_fragment, container, false)

        val fab = view?.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { presenter.onAddAlarmClicked() }

        val recyclerView = view?.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(getActivity())

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                if(viewHolder != null) {
                    presenter.removeAlarm(viewHolder.adapterPosition)
                    recyclerView.adapter.notifyItemRemoved(viewHolder.adapterPosition)
                    fab.show()
                }
            }
        }
        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return view
    }

    override fun onResume() {
        super.onResume()

        presenter.onResume()
    }

    override fun showTimePicker() {
        val tpd: TimePickerDialog = TimePickerDialog.newInstance(this, 0, 0, true);
        tpd.show(getFragmentManager(), "TimePickerDialog");
    }

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

    override fun onCheckedChange(position: Int, isChecked: Boolean) {
        presenter.onCheckedChanged(position, isChecked)
    }

    override fun getContext(): Context? {
        return getActivity()
    }

    override fun setAlarms(alarms: ArrayList<Alarm>) {
        recyclerView.adapter = RecyclerAdapter(this, alarms)
    }

    override fun onAlarmAdded(alarm: Alarm) {
        recyclerView.adapter.notifyItemInserted(recyclerView.adapter.getItemCount() - 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}