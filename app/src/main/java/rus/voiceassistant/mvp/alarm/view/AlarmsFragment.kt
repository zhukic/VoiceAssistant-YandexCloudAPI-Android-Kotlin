package rus.voiceassistant.mvp.alarm.view

import android.app.AlarmManager
import android.app.Fragment
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    lateinit var presenter: IAlarmPresenter
    var pendingIntent: PendingIntent? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.alarm_fragment, container, false)

        presenter = AlarmPresenter(this)

        val recyclerView = view?.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(getActivity())
        recyclerView.adapter = RecyclerAdapter(this, presenter.getAlarmsList())

        return view
    }

    override fun onAlarmOn(alarm: Alarm) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_HOUR, alarm.hour)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, alarm.minute)
        intent.putExtra(AlarmClock.EXTRA_IS_PM, true)
        pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0)
        startActivity(intent)
    }

    override fun onAlarmOff(alarm: Alarm) {
        val intent = Intent(AlarmClock.ACTION_DISMISS_ALARM)
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false)
        /*intent.putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_TIME)
        intent.putExtra(AlarmClock.EXTRA_HOUR, alarm.hour)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, alarm.minute)
        intent.putExtra(AlarmClock.EXTRA_IS_PM, true)
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, )
        startActivity(intent)*/
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

    override fun addAlarm(alarm: Alarm) {
        presenter.onAlarmAdded(alarm)
        recyclerView.adapter.notifyItemInserted(presenter.getAlarmsList().size - 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}