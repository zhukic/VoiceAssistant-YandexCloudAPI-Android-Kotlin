package rus.voiceassistant.presenter

/**
 * Created by RUS on 28.04.2016.
 */
interface IPresenter<T> {

    fun onResume()

    fun onAddActionClicked()

    fun removeAction(position: Int)

    fun onDestroy()
}