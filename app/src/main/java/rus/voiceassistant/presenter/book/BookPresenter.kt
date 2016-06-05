package rus.voiceassistant.presenter.book

import android.content.Intent
import org.json.JSONObject
import rus.voiceassistant.presenter.voice.ActionBuilderFromResponse
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.Logger
import rus.voiceassistant.MyApplication
import rus.voiceassistant.database.DatabaseManager
import rus.voiceassistant.model.actions.Book
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.model.yandex.YandexResponse
import rus.voiceassistant.presenter.ItemPresenter
import rus.voiceassistant.presenter.voice.DownloadInteractor
import rus.voiceassistant.presenter.voice.IDownloadInteractor
import rus.voiceassistant.view.ItemView
import java.net.URLEncoder
import java.util.*

/**
 * Created by RUS on 04.05.2016.
 */
class BookPresenter(var view: ItemView<Book>?): ItemPresenter<Book> {

    val books: ArrayList<Book> = DatabaseManager.getBooksListFromDatabase()

    override fun onResume() {
        books.sort()
        view?.setActions(books)
    }

    override fun onAddActionClicked() {
        view?.showItemFragmentDialog()
    }

    override fun onActionClicked(position: Int) {
        view?.showItemFragmentDialog(books[position])
    }

    override fun onLongActionClicked(position: Int) {
        view?.showDeleteItemFragmentDialog(position)
    }

    override fun onItemCreated(item: Book) {
        books.add(item)
        books.sort()
        MyApplication.bookDao.create(item)
        view?.onDataSetChanged()
    }

    override fun onItemEdited(item: Book) {
        MyApplication.bookDao.update(item)
        books.sort()
        view?.onDataSetChanged()
    }

    override fun removeAction(position: Int) {
        MyApplication.notificationDao.deleteById(books[position].id)
        books.removeAt(position)
        view?.onDataSetChanged()
    }

    override fun onDestroy() {
        this.view = null
    }

}