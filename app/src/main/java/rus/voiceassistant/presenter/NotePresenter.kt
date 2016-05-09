package rus.voiceassistant.presenter

import android.content.Intent
import org.json.JSONObject
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.Logger
import rus.voiceassistant.view.INoteView
import java.net.URLEncoder

/**
 * Created by RUS on 04.05.2016.
 */
class NotePresenter(var view: INoteView?): INotePresenter, IDownloadInteractor.OnFinishedListener {

    var text: String? = null

    companion object {
        val TAG = "TAG"

        val URL = "https://vins-markup.voicetech.yandex.net/markup/0.x/?text="
        val LAYERS = "&layers=OriginalRequest,ProcessedRequest,Tokens,Date"
        val API = "&key=8b1a122c-9942-4f0d-a1a6-10a18353131f"
        val TEXT = "Напомни через 15 минут сделать кофе"
    }

    override fun onRecognitionStarted() {
        //view?.startRecognitionActivity()
        resultOK(URLEncoder.encode(TEXT, "UTF-8"))
    }

    override fun onRecognitionFinished(requestCode: Int, resultCode: Int, data: Intent?) {
        when(resultCode) {
            RecognizerActivity.RESULT_OK -> resultOK(data?.getStringExtra(RecognizerActivity.EXTRA_RESULT) as String)
            RecognizerActivity.RESULT_ERROR -> Logger.log("VoiceError")
        }
    }


    override fun onDownloadFinished(json: JSONObject) {
    }

    override fun onDownloadError(t: Throwable) {
    }

    fun resultOK(text: String?) {
        val downloadInteractor = DownloadInteractor()
        downloadInteractor.downloadJson(text ?: "", this)
    }

    override fun onDestroy() {
        this.view = null
    }

}