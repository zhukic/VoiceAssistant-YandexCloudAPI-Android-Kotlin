package rus.voiceassistant.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
import kotlinx.android.synthetic.main.alarm_item.view.*
import kotlinx.android.synthetic.main.notification_item.view.*
import kotlinx.android.synthetic.main.subheader_item.view.*
import rus.voiceassistant.Logger
import rus.voiceassistant.R
import rus.voiceassistant.model.actions.Alarm
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.toInt
import java.util.*

/**
 * Created by RUS on 10.04.2016.
 */
class NotificationsAdapter(val onItemClickListener: OnItemClickListener, val items: List<Notification>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            val textTime: TextView = v.textTime,
            val notificationText: TextView = v.notificationText ) : RecyclerView.ViewHolder(v)

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

            holder.textTime.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)
            holder.notificationText.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)

            holder.textTime.text ="${items[realPosition].getDateString()} ${items[realPosition].getTimeString()}"
            holder.notificationText.text = "${items[realPosition].text}"

            holder.itemView?.setOnClickListener { onItemClickListener.onItemClicked(getRealPosition(holder.adapterPosition)) }
            holder.itemView?.setOnLongClickListener { onItemClickListener.onLongItemClicked(getRealPosition(holder.adapterPosition)) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        if(viewType == TYPE_ITEM)
            return ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.notification_item, parent, false))
        else if(viewType == TYPE_HEADER_COMPLETED)
            return SubHeaderViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.subheader_item, parent, false), "Выполненные")
        else if(viewType == TYPE_HEADER_CURRENT)
            return SubHeaderViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.subheader_item, parent, false), "Текущие")
        return null
    }

    override fun getItemViewType(position: Int): Int {
        if(position == items.indexOfFirst { it.isDone.equals(true) })
            return TYPE_HEADER_COMPLETED
        if(position == items.indexOfFirst { it.isDone.equals(false) } + isHeaderCompletedExist.toInt())
            return TYPE_HEADER_CURRENT
        else return TYPE_ITEM
    }

    private fun getRealPosition(position: Int): Int {
        var realPosition = position
        if(isHeaderCompletedExist)
            realPosition--;
        if(isHeaderCurrentExist && position > items.indexOfFirst { it.isDone.equals(false) })
            realPosition--;
        return realPosition
    }

    private fun checkHeaders() {
        isHeaderCompletedExist = items.indexOfFirst { it.isDone.equals(true) } != -1
        isHeaderCurrentExist = items.indexOfFirst { it.isDone.equals(false) } != -1
    }

    override fun getItemCount(): Int {
        checkHeaders()
        return items.size + isHeaderCompletedExist.toInt() + isHeaderCurrentExist.toInt()
    }

}