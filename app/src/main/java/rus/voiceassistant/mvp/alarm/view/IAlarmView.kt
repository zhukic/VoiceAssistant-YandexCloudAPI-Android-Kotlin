package rus.voiceassistant.mvp.alarm.view

import android.content.Context
import android.widget.TimePicker
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import rus.voiceassistant.mvp.alarm.model.Alarm
import java.text.FieldPosition
import java.util.*

/**
 * Created by RUS on 12.04.2016.
 */
interface IAlarmView : TimePickerDialog.OnTimeSetListener {

    fun showTimePicker()

    fun onAlarmAdded(alarm: Alarm)

    fun setAlarms(alarms: ArrayList<Alarm>)

    fun onCheckedChange(position: Int, isChecked: Boolean)

    fun getContext(): Context?

    fun onAlarmOnOff(alarm: Alarm)

}