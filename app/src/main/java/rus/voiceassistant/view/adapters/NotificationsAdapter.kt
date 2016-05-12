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
import rus.voiceassistant.R
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.view.alarm.IAlarmView
import rus.voiceassistant.isDateComesToday
import rus.voiceassistant.model.Notification
import rus.voiceassistant.view.notification.INotificationView
import java.util.*

/**
 * Created by RUS on 10.04.2016.
 */
class NotificationsAdapter(val onItemClickListener: OnItemClickListener, val items: List<Notification>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TYPE_HEADER = 0
        val TYPE_ITEM = 1
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int)

        fun onLongItemClicked(position: Int): Boolean
    }

    class ItemViewHolder(
            val v: View,
            val textTime: TextView = v.textTime,
            val notificationText: TextView = v.notificationText ) : RecyclerView.ViewHolder(v) {
    }

    class SubHeaderViewHolder(val v: View, val text: TextView = v.subHeader) : RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if(holder is SubHeaderViewHolder) {

        }

        if(holder is ItemViewHolder?) {
            val context = holder?.itemView?.context

            holder?.textTime?.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)
            holder?.notificationText?.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)

            holder?.textTime?.text ="${items[position].getDateString()} ${items[position].getTimeString()}"
            holder?.notificationText?.text = "${items[position].text}(${items[position].isDone})"

            holder?.itemView?.setOnClickListener { onItemClickListener.onItemClicked(holder.adapterPosition) }
            holder?.itemView?.setOnLongClickListener { onItemClickListener.onLongItemClicked(holder.adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        if(viewType == TYPE_ITEM)
            return ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.notification_item, parent, false))
        else if(viewType == TYPE_HEADER)
            return SubHeaderViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.subheader_item, parent, false))
        return null
    }


    override fun getItemViewType(position: Int): Int {
        return TYPE_ITEM
    }


    override fun getItemCount(): Int = items.size

}