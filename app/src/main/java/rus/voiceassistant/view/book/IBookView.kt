package rus.voiceassistant.view.book

import rus.voiceassistant.model.actions.Book
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.view.IView

/**
 * Created by RUS on 04.05.2016.
 */
interface IBookView : IView<Book> {

    fun showBookDialog(book: Book? = null)

}