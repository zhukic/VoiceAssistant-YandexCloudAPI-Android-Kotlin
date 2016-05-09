package rus.voiceassistant.view.note

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.notes_fragment.*
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.R
import rus.voiceassistant.main.view.MainActivity
import rus.voiceassistant.model.Notification
import rus.voiceassistant.presenter.note.INotePresenter
import rus.voiceassistant.presenter.note.NotePresenter
import rus.voiceassistant.receivers.NotificationReceiver
import java.util.*

/**
 * Created by RUS on 03.05.2016.
 */
class NotesFragment : Fragment(), INoteView {

    lateinit var presenter: INotePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.notes_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        presenter = NotePresenter(this)
        fab.setOnClickListener { presenter.onRecognitionStarted() }
    }

    override fun startRecognitionActivity() {
        val intent = Intent(activity, RecognizerActivity::class.java)
        intent.putExtra(RecognizerActivity.EXTRA_LANGUAGE, MainActivity.EXTRA_LANGUAGE)
        intent.putExtra(RecognizerActivity.EXTRA_MODEL, MainActivity.EXTRA_MODEL)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onRecognitionFinished(requestCode, resultCode, data)
    }

    override fun createNotification(notification: Notification) {
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(activity, NotificationReceiver::class.java)
        notificationIntent.putExtra("TEXT", notification.text)
        notificationIntent.putExtra("ID", notification.id)
        val pendingIntent = PendingIntent.getBroadcast(activity, notification.id, notificationIntent, PendingIntent.FLAG_ONE_SHOT)
        val calendar = Calendar.getInstance()
        with(calendar) {
            setTimeInMillis(System.currentTimeMillis())
            set(Calendar.YEAR, notification.year)
            set(Calendar.MONTH, notification.month)
            set(Calendar.DAY_OF_MONTH, notification.day)
            set(Calendar.HOUR_OF_DAY, notification.hour)
            set(Calendar.MINUTE, notification.minute)
            set(Calendar.SECOND, 0)
        }
        alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
    }

    override fun onError() {
        textView.text = "ERROR"
    }

}