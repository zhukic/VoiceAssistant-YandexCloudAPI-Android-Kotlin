package rus.voiceassistant.view.notification

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.create_notification.*
import rus.voiceassistant.Logger
import rus.voiceassistant.R
import rus.voiceassistant.model.Notification
import rus.voiceassistant.view.notification.NotificationCreationListener
import java.util.*

/**
 * Created by RUS on 30.04.2016.
 */
class CreateNotificationFragmentDialog() : DialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    companion object {

        val MODE_CREATE = 0
        val MODE_EDIT = 1

        fun newInstance(notification: Notification? = null, mode: Int = 0): CreateNotificationFragmentDialog {
            if(mode != MODE_CREATE && mode != MODE_EDIT)
                throw IllegalArgumentException("Invalid mode")

            val fragment = CreateNotificationFragmentDialog()
            val args = Bundle()
            args.putSerializable("NOTIFICATION", notification)
            args.putInt("MODE", mode)
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var notification: Notification
    var mode: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.create_notification, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        //dialog.setTitle(R.string.addNotfication)

        mode = arguments.getInt("MODE")

        if(mode == 0) {
            notification = Notification()
            //editNotificationDate.setText(notification.getDateString())
        } else {
            notification = arguments.getSerializable("NOTIFICATION") as Notification
            editNotificationText.setText(notification.text)
            editNotificationTime.setText(notification.getTimeString())
            editNotificationDate.setText(notification.getDateString())
        }

        btnCancel.typeface = Typer.set(context).getFont(Font.ROBOTO_MEDIUM)
        btnOk.typeface = Typer.set(context).getFont(Font.ROBOTO_MEDIUM)

        editNotificationDate.setOnFocusChangeListener { view, b -> if(b) showDatePicker()  }
        editNotificationTime.setOnFocusChangeListener { view, b -> if(b) showTimePicker()  }

        btnCancel.setOnClickListener { dismiss() }
        btnOk.setOnClickListener { finish() }

        editNotificationText.requestFocus()
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val dpd: DatePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(activity.fragmentManager, "DatePicker")
    }

    fun showTimePicker() {
        val tpd: TimePickerDialog = TimePickerDialog.newInstance(this, 0, 0, true);
        tpd.show(activity.fragmentManager, "TimePicker")
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        notificationDateText.visibility = View.VISIBLE
        Logger.log("month = $monthOfYear")
        notification.day = dayOfMonth
        notification.month = monthOfYear
        notification.year = year
        editNotificationDate.setText(notification.getDateString())
        editNotificationDate.clearFocus()

    }

    override fun onTimeSet(view: RadialPickerLayout?, hourOfDay: Int, minute: Int, second: Int) {
        notificationTimeText.visibility = View.VISIBLE
        notification.minute = minute
        notification.hour = hourOfDay
        editNotificationTime.setText(notification.getTimeString())
        editNotificationTime.clearFocus()
    }

    fun finish() {
        notification.text = editNotificationText.text.toString()
        val notificationsFragment = targetFragment as NotificationCreationListener

        if(mode == 0)
            notificationsFragment.onNotificationCreated(notification)
        else
            notificationsFragment.onNotificationEdited(notification)

        dismiss()
    }

}