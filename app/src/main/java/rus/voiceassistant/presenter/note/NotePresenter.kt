package rus.voiceassistant.presenter.note

import android.content.Intent
import org.json.JSONObject
import rus.voiceassistant.presenter.voice.ActionBuilder
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.Logger
import rus.voiceassistant.MyApplication
import rus.voiceassistant.model.Notification
import rus.voiceassistant.model.yandex.YandexResponse
import rus.voiceassistant.presenter.voice.DownloadInteractor
import rus.voiceassistant.presenter.voice.IDownloadInteractor
import rus.voiceassistant.view.note.INoteView
import java.net.URLEncoder

/**
 * Created by RUS on 04.05.2016.
 */
class NotePresenter(var view: INoteView?): INotePresenter, IDownloadInteractor.OnFinishedListener {

    override fun onRecognitionStarted() {
        //view?.startRecognitionActivity()
        //resultOK(URLEncoder.encode(TEXT, "UTF-8"))
        resultOK()
    }

    override fun onRecognitionFinished(requestCode: Int, resultCode: Int, data: Intent?) {
        when(resultCode) {
            RecognizerActivity.RESULT_OK -> resultOK(data?.getStringExtra(RecognizerActivity.EXTRA_RESULT) as String)
            RecognizerActivity.RESULT_ERROR -> Logger.log("VoiceError")
        }
    }

    fun resultOK(text: String? = "") {
        val downloadInteractor = DownloadInteractor()
        downloadInteractor.makeRequestToYandexServer(text ?: "", this)
    }


    override fun onDownloadFinished(yandexResponse: YandexResponse) {
        val actionBuilder: ActionBuilder = ActionBuilder(yandexResponse)
        val action = actionBuilder.getAction() as Notification
        MyApplication.notificationDao.create(action)
        view?.createNotification(action)
        Logger.log(action.toString())
    }

    override fun onDownloadError(t: Throwable) {
    }

    override fun onDestroy() {
        this.view = null
    }

}