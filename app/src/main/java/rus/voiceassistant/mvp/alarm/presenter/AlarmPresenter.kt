package rus.voiceassistant.mvp.alarm.presenter

import android.util.Log
import rus.voiceassistant.MyApplication
import rus.voiceassistant.database.DatabaseManager
import rus.voiceassistant.mvp.alarm.model.Alarm
import rus.voiceassistant.mvp.alarm.view.IAlarmView
import java.util.*

/**
 * Created by RUS on 12.04.2016.
 */
class AlarmPresenter(var view: IAlarmView?) : IAlarmPresenter {

    companion object {
        val TAG = "TAG"
    }

    val alarms: ArrayList<Alarm> = DatabaseManager.getAlarmsListFromDatabase()

    override fun onCheckedChanged(position: Int, isChecked: Boolean) {
        Log.i(TAG, position.toString() + " " + isChecked)
        /*if(isChecked == true)
            view?.onAlarmOn(alarms.get(position))
        else
            view?.onAlarmOff(alarms.get(position))*/
    }

    override fun onResume() {
        view?.setAlarms(alarms)
    }

    override fun onAddAlarmClicked() {
        view?.showTimePicker()
    }

    override fun removeAlarm(position: Int) {
        DatabaseManager.remove(alarms.get(position).id)
        alarms.removeAt(position)
    }

    override fun createAlarm(hourOfDay: Int, minute: Int) {
        val alarm = Alarm()
        alarm.hour = hourOfDay
        alarm.minute = minute
        alarms.add(alarm)
        MyApplication.alarmDao?.create(alarm)
        view?.onAlarmAdded(alarm)
    }

    override fun onDestroy() {
        view = null
    }

}