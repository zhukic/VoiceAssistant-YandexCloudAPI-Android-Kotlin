package rus.voiceassistant.mvp.alarm.presenter

import android.util.Log
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

    val alarms: ArrayList<Alarm> = ArrayList()

    override fun onCheckedChanged(position: Int, isChecked: Boolean) {
        Log.i(TAG, position.toString() + " " + isChecked)
        /*if(isChecked == true)
            view?.onAlarmOn(alarms.get(position))
        else
            view?.onAlarmOff(alarms.get(position))*/
    }

    override fun onAlarmAdded(alarm: Alarm) {
        alarms.add(alarm)
    }

    override fun getAlarmsList(): ArrayList<Alarm> {
        return alarms
    }

    override fun onDestroy() {
        view = null
    }

}