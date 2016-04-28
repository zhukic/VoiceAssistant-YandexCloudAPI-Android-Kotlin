package rus.voiceassistant.view

import android.content.Context
import rus.voiceassistant.model.Alarm
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
interface IView<T> {

    fun showSnackbar(text: String)

    fun onActionAdded(action: T)

    fun setActions(actions: ArrayList<T>)

    fun onActionClicked(action: T)
}