package rus.voiceassistant.main.presenter

import android.content.Intent

/**
 * Created by RUS on 25.03.2016.
 */
interface IPresenter {

    fun observe(text: String?)

    fun onRecognitionStarted()

    fun onRecognitionFinished(requestCode: Int, resultCode: Int, data: Intent?)

    fun onDestroy()
}