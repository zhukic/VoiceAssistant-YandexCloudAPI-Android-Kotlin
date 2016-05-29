package rus.voiceassistant.view.book

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.create_book.*
import rus.voiceassistant.R
import rus.voiceassistant.model.actions.Book
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.notification
import rus.voiceassistant.view.notification.ItemCreationListener
import java.util.*

/**
 * Created by RUS on 29.05.2016.
 */
class CreateBookFragmentDialog() : DialogFragment() {

    companion object {

        val MODE_CREATE = 0
        val MODE_EDIT = 1

        val KEY_MODE = "MODE"
        val KEY_BOOK = "BOOK"

        fun newInstance(book: Book? = null, mode: Int = 0): CreateBookFragmentDialog {
            if(mode != MODE_CREATE && mode != MODE_EDIT)
                throw IllegalArgumentException("Invalid mode")

            val fragment = CreateBookFragmentDialog()
            val args = Bundle()
            args.putSerializable(KEY_BOOK, book)
            args.putInt(KEY_MODE, mode)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var book: Book
    private var mode: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.create_book, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        mode = arguments.getInt(KEY_MODE)

        if(mode == 0) {
            book = Book();
        } else {
            book = arguments.getSerializable(KEY_BOOK) as Book
            editBookAuthor.setText(book.author)
            editBookName.setText(book.name)
            checkboxIsBookRead.isChecked = book.isRead
        }

        btnCancel.typeface = Typer.set(context).getFont(Font.ROBOTO_MEDIUM)
        btnOk.typeface = Typer.set(context).getFont(Font.ROBOTO_MEDIUM)

        btnCancel.setOnClickListener { dismiss() }
        btnOk.setOnClickListener { finish() }

        editBookAuthor.requestFocus()
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    fun finish() {
        book.author = editBookAuthor.text.toString()
        book.name = editBookName.text.toString();
        book.isRead = checkboxIsBookRead.isChecked

        val booksFragment = targetFragment as ItemCreationListener<Book>
        if(mode == 0)
            booksFragment.onItemCreated(book)
        else
            booksFragment.onItemEdited(book)

        dismiss()
    }

}