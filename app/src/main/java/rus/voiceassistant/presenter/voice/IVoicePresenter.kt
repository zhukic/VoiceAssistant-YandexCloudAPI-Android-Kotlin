package rus.voiceassistant.presenter.voice

import android.content.Intent

/**
 * Created by RUS on 10.05.2016.
 */
interface IVoicePresenter {

    fun onResult(requestCode: Int, resultCode: Int, data: Intent)

    fun onDestroy()

}