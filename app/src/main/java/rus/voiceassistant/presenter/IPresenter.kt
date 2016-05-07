package rus.voiceassistant.presenter

import java.text.FieldPosition

/**
 * Created by RUS on 28.04.2016.
 */
interface IPresenter<T> {

    fun onResume()

    fun onAddActionClicked()

    fun onActionClicked(position: Int)

    fun onLongActionClicked(position: Int)

    fun removeAction(position: Int)

    fun onDestroy()
}