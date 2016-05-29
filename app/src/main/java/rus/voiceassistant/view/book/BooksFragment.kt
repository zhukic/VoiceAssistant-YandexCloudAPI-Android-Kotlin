package rus.voiceassistant.view.book

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.books_fragment.*
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.ActionCreator
import rus.voiceassistant.R
import rus.voiceassistant.MainActivity
import rus.voiceassistant.model.actions.Book
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.presenter.book.BookPresenter
import rus.voiceassistant.presenter.book.IBookPresenter
import rus.voiceassistant.presenter.notification.INotificationPresenter
import rus.voiceassistant.presenter.notification.NotificationPresenter
import rus.voiceassistant.receivers.NotificationReceiver
import rus.voiceassistant.toast
import rus.voiceassistant.view.adapters.BooksAdapter
import rus.voiceassistant.view.adapters.NotificationsAdapter
import rus.voiceassistant.view.notification.CreateNotificationFragmentDialog
import rus.voiceassistant.view.notification.INotificationView
import rus.voiceassistant.view.notification.ItemCreationListener
import java.util.*

/**
 * Created by RUS on 03.05.2016.
 */
class BooksFragment : Fragment(), IBookView, ItemCreationListener<Book>, BooksAdapter.OnItemClickListener {

    val presenter: IBookPresenter = BookPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.books_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        fab.setOnClickListener { presenter.onAddActionClicked() }

        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onActionAdded() = recyclerView.adapter.notifyItemInserted(recyclerView.adapter.itemCount - 1)

    override fun onActionRemoved(position: Int) = recyclerView.adapter.notifyItemRemoved(position)

    override fun setActions(actions: ArrayList<Book>) {
        recyclerView.adapter = BooksAdapter(this, actions);
    }

    override fun onItemClicked(position: Int) = presenter.onActionClicked(position)

    override fun onLongItemClicked(position: Int): Boolean {
        presenter.onLongActionClicked(position)
        return true
    }

    override fun showBookDialog(book: Book?) {
        val fragmentManager = (activity as MainActivity).supportFragmentManager
        var createBookDialog: CreateBookFragmentDialog
        if(book == null)
            createBookDialog = CreateBookFragmentDialog.newInstance(mode = CreateNotificationFragmentDialog.MODE_CREATE)
        else createBookDialog = CreateBookFragmentDialog.newInstance(book, CreateNotificationFragmentDialog.MODE_EDIT)

        createBookDialog.setTargetFragment(this, 300)
        createBookDialog.show(fragmentManager, "fragment_create_book");
    }

    override fun showDeleteActionDialog(position: Int) {
        MaterialDialog.Builder(activity)
                .content(R.string.deleteNotificationTitle)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .onPositive { materialDialog: MaterialDialog, dialogAction: DialogAction -> presenter.removeAction(position)}
                .show()
    }

    override fun onItemCreated(item: Book) = presenter.onBookCreated(item)

    override fun onItemEdited(item: Book) = presenter.onBookEdited(item)

    override fun onDataSetChanged() = recyclerView.adapter.notifyDataSetChanged()

    override fun showSnackBar(text: String) = toast(text)

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}