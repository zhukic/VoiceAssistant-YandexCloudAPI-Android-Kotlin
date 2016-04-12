package rus.voiceassistant.mvp.alarm.presenter

import rus.voiceassistant.mvp.alarm.model.Alarm
import java.util.*

/**
 * Created by RUS on 12.04.2016.
 */
interface IAlarmPresenter {

    fun onCheckedChanged(position: Int, isChecked: Boolean)

    fun onAlarmAdded(alarm: Alarm)

    fun getAlarmsList(): ArrayList<Alarm>

    fun onDestroy()

}