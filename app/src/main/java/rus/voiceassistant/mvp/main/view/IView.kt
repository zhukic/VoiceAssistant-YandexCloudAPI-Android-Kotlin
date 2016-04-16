package rus.voiceassistant.mvp.main.view

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog

/**
 * Created by RUS on 25.03.2016.
 */
interface IView {

    fun startRecognitionActivity()

    fun onError()

}