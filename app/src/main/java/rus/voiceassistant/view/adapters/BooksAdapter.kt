package rus.voiceassistant.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
import kotlinx.android.synthetic.main.book_item.view.*
import kotlinx.android.synthetic.main.subheader_item.view.*
import rus.voiceassistant.Logger
import rus.voiceassistant.R
import rus.voiceassistant.model.actions.Book
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.toInt

/**
 * Created by RUS on 29.05.2016.
 */
class BooksAdapter(val onItemClickListener: OnItemClickListener, val items: List<Book>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TYPE_HEADER_COMPLETED = 0
        val TYPE_HEADER_CURRENT = 1
        val TYPE_ITEM = 2
    }

    private var isHeaderCompletedExist: Boolean = false
    private var isHeaderCurrentExist: Boolean = false

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
        fun onLongItemClicked(position: Int): Boolean
    }

    class ItemViewHolder(
            val v: View,
            val bookAuthor: TextView = v.bookAuthor,
            val bookName: TextView = v.bookName) : RecyclerView.ViewHolder(v)

    class SubHeaderViewHolder(val v: View,
                              val text: String,
                              val textView: TextView = v.subHeader) : RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if(holder is SubHeaderViewHolder) {
            holder.textView.typeface = Typer.set(holder.textView.context).getFont(Font.ROBOTO_MEDIUM)
            holder.textView.text = holder.text
        } else if(holder is ItemViewHolder) {
            var realPosition = getRealPosition(position)
            val context = holder.itemView.context

            holder.bookAuthor.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)
            holder.bookName.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)

            holder.bookAuthor.text =items[realPosition].author
            holder.bookName.text = items[realPosition].name

            holder.itemView?.setOnClickListener { onItemClickListener.onItemClicked(getRealPosition(holder.adapterPosition)) }
            holder.itemView?.setOnLongClickListener { onItemClickListener.onLongItemClicked(getRealPosition(holder.adapterPosition)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        if(viewType == TYPE_ITEM)
            return ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.book_item, parent, false))
        else if(viewType == TYPE_HEADER_COMPLETED)
            return SubHeaderViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.subheader_item, parent, false), "Прочитанные")
        else if(viewType == TYPE_HEADER_CURRENT)
            return SubHeaderViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.subheader_item, parent, false), "Непрочитанные")
        return null
    }

    override fun getItemViewType(position: Int): Int {
        if(position == items.indexOfFirst { it.isRead.equals(true) })
            return TYPE_HEADER_COMPLETED
        if(position == items.indexOfFirst { it.isRead.equals(false) } + isHeaderCompletedExist.toInt())
            return TYPE_HEADER_CURRENT
        else return TYPE_ITEM
    }

    private fun getRealPosition(position: Int): Int {
        var realPosition = position
        if(isHeaderCompletedExist)
            realPosition--;
        if(isHeaderCurrentExist && position > items.indexOfFirst { it.isRead.equals(false) })
            realPosition--;
        return realPosition
    }

    private fun checkHeaders() {
        isHeaderCompletedExist = items.indexOfFirst { it.isRead.equals(true) } != -1
        isHeaderCurrentExist = items.indexOfFirst { it.isRead.equals(false) } != -1
    }

    override fun getItemCount(): Int {
        checkHeaders()
        return items.size + isHeaderCompletedExist.toInt() + isHeaderCurrentExist.toInt()
    }

}