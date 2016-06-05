package rus.voiceassistant.presenter

import rus.voiceassistant.model.actions.Book
import java.text.FieldPosition

/**
 * Created by RUS on 28.04.2016.
 */
interface ItemPresenter<T> {

    fun onResume()

    fun onAddActionClicked()

    fun onActionClicked(position: Int)

    fun onLongActionClicked(position: Int)

    fun removeAction(position: Int)

    fun onItemCreated(item: T)

    fun onItemEdited(item: T)

    fun onDestroy()
}