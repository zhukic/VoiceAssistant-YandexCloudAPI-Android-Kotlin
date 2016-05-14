package rus.voiceassistant.presenter.voice

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.speech.RecognizerIntent
import rus.voiceassistant.presenter.voice.ActionBuilderFromResponse
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.Logger
import rus.voiceassistant.MyApplication
import rus.voiceassistant.createNotification
import rus.voiceassistant.model.actions.Alarm
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.model.yandex.GoogleSearchAction
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
            RecognizerActivity.RESULT_OK -> {
                resultOK(data.getStringExtra(RecognizerActivity.EXTRA_RESULT))
            }
            RecognizerActivity.RESULT_ERROR -> {
                view?.finishActivity()
            }
        }
    }

    fun resultOK(text: String) {
        Logger.log(text)
        view?.showToast(text)
        val downloadInteractor = DownloadInteractor()
        downloadInteractor.makeRequestToYandexServer(text, this)
    }

    override fun onDownloadFinished(yandexResponse: YandexResponse) {
        val actionBuilderFromResponse: ActionBuilderFromResponse = ActionBuilderFromResponse(yandexResponse)
        val action = actionBuilderFromResponse.getAction()
        if(action is Notification) {
            MyApplication.notificationDao.create(action)
            createNotification(action)
            Logger.log(action.toString())
        } else if(action is Alarm) {
            Logger.log(action.toString())
            view?.showToast("Alarm created")
        } else if(action is GoogleSearchAction) {
            openBrowser(action.text)
        }
        view?.finishActivity()
    }

    fun openBrowser(text: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.google.ru/search?q=$text"))
        view?.getContext()?.startActivity(intent)
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