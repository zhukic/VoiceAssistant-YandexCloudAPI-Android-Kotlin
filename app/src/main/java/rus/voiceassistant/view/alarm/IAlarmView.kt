package rus.voiceassistant.view.alarm

import android.content.Context
import android.widget.TimePicker
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.view.IView
import java.text.FieldPosition
import java.util.*

/**
 * Created by RUS on 12.04.2016.
 */
interface IAlarmView : IView<Alarm>, TimePickerDialog.OnTimeSetListener {

    fun showTimePicker()

    fun onCheckedChange(position: Int, isChecked: Boolean)

    fun getContext(): Context?

    fun onAlarmOnOff(alarm: Alarm)

}