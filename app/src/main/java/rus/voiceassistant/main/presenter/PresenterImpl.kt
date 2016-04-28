package rus.voiceassistant.main.presenter

import android.content.Intent
import android.util.Log
import org.json.JSONObject
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.main.view.IView
import rx.Observable
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URLConnection
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

/**
 * Created by RUS on 25.03.2016.
 */
class PresenterImpl(var view: IView?) : IPresenter, ObserveListener {

    var text: String? = null

    companion object {
        val TAG = "TAG"

        val URL = "https://vins-markup.voicetech.yandex.net/markup/0.x/?text="
        val LAYERS = "&layers=OriginalRequest,ProcessedRequest,Tokens,Date"
        val API = "&key=8b1a122c-9942-4f0d-a1a6-10a18353131f"
    }

    override fun onRecognitionStarted() {
        view?.startRecognitionActivity()
    }

    override fun onRecognitionFinished(requestCode: Int, resultCode: Int, data: Intent?) {
        when(resultCode) {
            RecognizerActivity.RESULT_OK -> resultOK(data?.getStringExtra(RecognizerActivity.EXTRA_RESULT) as String)
            RecognizerActivity.RESULT_ERROR -> resultError((data?.getSerializableExtra(RecognizerActivity.EXTRA_ERROR) as Error).message)
        }
    }

    override fun observe(text: String?) {
        resultOK(text)
    }

    override fun onObserveFinished(text: String) {

    }

    fun resultOK(text: String?) {
        val myObserver = MyObserver(this)
        myObserver.observe(text)
    }

    fun resultError(text: String?) {

    }

    override fun onDestroy() {
        this.view = null
    }

}