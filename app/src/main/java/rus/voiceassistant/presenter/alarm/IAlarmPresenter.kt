package rus.voiceassistant.presenter.alarm

import rus.voiceassistant.model.Alarm
import rus.voiceassistant.presenter.IPresenter
import java.util.*

/**
 * Created by RUS on 12.04.2016.
 */
interface IAlarmPresenter : IPresenter<Alarm> {

    fun createAlarm(hourOfDay: Int, minute: Int)

    fun onCheckedChanged(position: Int, isChecked: Boolean)

}