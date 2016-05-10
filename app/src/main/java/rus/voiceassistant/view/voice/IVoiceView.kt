package rus.voiceassistant.view.voice

import android.content.Context

/**
 * Created by RUS on 10.05.2016.
 */
interface IVoiceView {

    fun getContext(): Context

    fun showToast(text: String)

    fun finishActivity()

}