package rus.voiceassistant.presenter

import org.json.JSONObject

/**
 * Created by RUS on 04.05.2016.
 */
interface IDownloadInteractor {

    interface OnFinishedListener {

        fun onDownloadFinished(json: JSONObject)

        fun onDownloadError(t: Throwable)

    }

    fun downloadJson(text: String, onFinishedListener: OnFinishedListener)

}