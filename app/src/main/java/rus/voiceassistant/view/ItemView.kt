package rus.voiceassistant.view

import android.content.Context
import rus.voiceassistant.model.actions.Alarm
import rus.voiceassistant.model.actions.Book
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
interface ItemView<T> {

    fun getContext(): Context

    fun showSnackBar(text: String)

    fun onActionAdded()

    fun onActionRemoved(position: Int)

    fun setActions(actions: ArrayList<T>)

    fun onDataSetChanged()

    fun showItemFragmentDialog(t: T? = null)

    fun showDeleteItemFragmentDialog(position: Int)
}