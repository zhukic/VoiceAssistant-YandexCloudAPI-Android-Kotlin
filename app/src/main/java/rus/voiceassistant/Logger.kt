package rus.voiceassistant

import android.util.Log

/**
 * Created by RUS on 28.04.2016.
 */
class Logger {

    companion object {
        fun log(message: String) {
            Log.i("TAG", message);
        }
    }
}