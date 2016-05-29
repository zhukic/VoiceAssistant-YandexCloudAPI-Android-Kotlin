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
import rus.voiceassistant.presenter.voice.DownloadInteractor
import rus.voiceassistant.presenter.voice.IDownloadInteractor
import rus.voiceassistant.view.book.IBookView
import java.net.URLEncoder
import java.util.*

/**
 * Created by RUS on 04.05.2016.
 */
class BookPresenter(var view: IBookView?): IBookPresenter {

    val books: ArrayList<Book> = DatabaseManager.getBooksListFromDatabase()

    override fun onResume() {
        books.sort()
        view?.setActions(books)
    }

    override fun onAddActionClicked() {
        view?.showBookDialog()
    }

    override fun onActionClicked(position: Int) {
        view?.showBookDialog(books[position])
    }

    override fun onLongActionClicked(position: Int) {
        view?.showDeleteActionDialog(position)
    }

    override fun onBookCreated(book: Book) {
        books.add(book)
        books.sort()
        MyApplication.bookDao.create(book)
        view?.onDataSetChanged()
    }

    override fun onBookEdited(book: Book) {
        MyApplication.bookDao.update(book)
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