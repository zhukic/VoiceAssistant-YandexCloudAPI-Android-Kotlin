package rus.voiceassistant.presenter

import android.content.Intent

/**
 * Created by RUS on 04.05.2016.
 */
interface INotePresenter {

    fun onRecognitionStarted()

    fun onRecognitionFinished(requestCode: Int, resultCode: Int, data: Intent?)

    fun onDestroy()

}