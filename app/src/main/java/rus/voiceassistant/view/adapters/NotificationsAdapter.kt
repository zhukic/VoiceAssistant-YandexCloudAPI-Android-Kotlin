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
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.view.alarm.IAlarmView
import rus.voiceassistant.isDateComesToday
import rus.voiceassistant.model.Notification
import rus.voiceassistant.toInt
import rus.voiceassistant.view.notification.INotificationView
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
    var headersCount = 0;

    interface OnItemClickListener {
        fun onItemClicked(position: Int)

        fun onLongItemClicked(position: Int): Boolean
    }

    class ItemViewHolder(
            val v: View,
            val textTime: TextView = v.textTime,
            val notificationText: TextView = v.notificationText ) : RecyclerView.ViewHolder(v) {
    }

    class SubHeaderViewHolder(val v: View,val text: String, val textView: TextView = v.subHeader) : RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if(holder is SubHeaderViewHolder) {
            holder.textView.typeface = Typer.set(holder.textView.context).getFont(Font.ROBOTO_MEDIUM)
            holder.textView.text = holder.text
        }

        if(holder is ItemViewHolder) {
            var newPosition = getRealPosition(position)
            val context = holder.itemView.context

            holder.textTime.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)
            holder.notificationText.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)

            holder.textTime.text ="default=$position"
            holder.notificationText.text = "real=$newPosition"

            holder.textTime.text ="${items[newPosition].getDateString()} ${items[newPosition].getTimeString()}"
            holder.notificationText.text = "${items[newPosition].text}(${items[newPosition].isDone})"

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
        Logger.log(items.indexOfFirst { it.isDone.equals(false) }.toString())
        Logger.log(items.indexOfFirst { it.isDone.equals(true) }.toString())
        Logger.log("---------------------------")
        if(position == items.indexOfFirst { it.isDone.equals(true) }) {
            isHeaderCompletedExist = true
            return TYPE_HEADER_COMPLETED
        }
        if(position == items.indexOfFirst { it.isDone.equals(false) } + isHeaderCompletedExist.toInt()) {
            isHeaderCurrentExist = true
            return TYPE_HEADER_CURRENT
        }
        else return TYPE_ITEM
    }

    private fun getRealPosition(position: Int): Int {
        var realPosition = position
        if(items.indexOfFirst { it.isDone.equals(true) } != -1)
            realPosition--;
        if(items.indexOfFirst { it.isDone.equals(false) } != -1 && position > items.indexOfFirst { it.isDone.equals(false) })
            realPosition--;
        return realPosition
    }

    override fun getItemCount(): Int = items.size + isHeaderCompletedExist.toInt() + isHeaderCurrentExist.toInt()

}