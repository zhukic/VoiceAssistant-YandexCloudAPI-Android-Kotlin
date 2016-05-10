package rus.voiceassistant.presenter.voice

import org.json.JSONObject
import rus.voiceassistant.model.yandex.YandexResponse

/**
 * Created by RUS on 04.05.2016.
 */
interface IDownloadInteractor {

    interface OnFinishedListener {

        fun onDownloadFinished(yandexResponse: YandexResponse)

        fun onDownloadError(t: Throwable)

    }

    fun makeRequestToYandexServer(text: String, onFinishedListener: OnFinishedListener)

}