package rus.voiceassistant.mvp.view

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog

/**
 * Created by RUS on 25.03.2016.
 */
interface IView: TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    fun startRecognitionActivity()

    fun showTimePicker()

    fun onError()

}