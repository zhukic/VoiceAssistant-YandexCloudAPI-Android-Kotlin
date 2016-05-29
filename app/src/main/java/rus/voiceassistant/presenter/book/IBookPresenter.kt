package rus.voiceassistant.presenter.book

import android.content.Intent
import rus.voiceassistant.model.actions.Book
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.presenter.IPresenter

/**
 * Created by RUS on 04.05.2016.
 */
interface IBookPresenter: IPresenter<Book> {

    fun onBookCreated(book: Book)

    fun onBookEdited(book: Book)

}