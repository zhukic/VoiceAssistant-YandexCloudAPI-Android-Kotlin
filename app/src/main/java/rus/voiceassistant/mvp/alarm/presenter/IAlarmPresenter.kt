package rus.voiceassistant.mvp.alarm.presenter

import rus.voiceassistant.mvp.alarm.model.Alarm
import java.util.*

/**
 * Created by RUS on 12.04.2016.
 */
interface IAlarmPresenter {

    fun onResume()

    fun createAlarm(hourOfDay: Int, minute: Int)

    fun onAddAlarmClicked()

    fun removeAlarm(position: Int)

    fun onCheckedChanged(position: Int, isChecked: Boolean)

    fun onDestroy()

}