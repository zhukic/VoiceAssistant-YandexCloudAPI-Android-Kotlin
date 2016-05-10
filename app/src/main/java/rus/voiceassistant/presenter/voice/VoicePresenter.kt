package rus.voiceassistant.presenter.voice

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import rus.voiceassistant.presenter.voice.ActionBuilder
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.Logger
import rus.voiceassistant.MyApplication
import rus.voiceassistant.createNotification
import rus.voiceassistant.model.Notification
import rus.voiceassistant.model.yandex.YandexResponse
import rus.voiceassistant.presenter.voice.DownloadInteractor
import rus.voiceassistant.presenter.voice.IDownloadInteractor
import rus.voiceassistant.receivers.NotificationReceiver
import rus.voiceassistant.view.voice.IVoiceView
import java.util.*

/**
 * Created by RUS on 10.05.2016.
 */
class VoicePresenter(var view: IVoiceView?) : IVoicePresenter, IDownloadInteractor.OnFinishedListener {

    override fun onResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (resultCode) {
            Activity.RESULT_OK -> {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                Logger.log(result[0])
                Logger.log(data.getStringExtra(RecognizerActivity.EXTRA_RESULT))
            }
            RecognizerActivity.RESULT_OK -> {
                resultOK(data.getStringExtra(RecognizerActivity.EXTRA_RESULT))
            }
        }
    }

    fun resultOK(text: String) {
        Logger.log(text)
        val downloadInteractor = DownloadInteractor()
        downloadInteractor.makeRequestToYandexServer(text, this)
    }

    override fun onDownloadFinished(yandexResponse: YandexResponse) {
        val actionBuilder: ActionBuilder = ActionBuilder(yandexResponse)
        val action = actionBuilder.getAction() as Notification
        MyApplication.notificationDao.create(action)
        createNotification(action)
        Logger.log(action.toString())
        view?.finishActivity()
    }

    fun createNotification(notification: Notification) {
        val alarmManager = view!!.getContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.createNotification(view!!.getContext(), notification)
    }

    override fun onDownloadError(t: Throwable) {
        view?.showToast("Error")
        view?.finishActivity()
    }

    override fun onDestroy() {
        view = null
    }
}