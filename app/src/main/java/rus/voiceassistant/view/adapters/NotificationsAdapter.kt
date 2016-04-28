package rus.voiceassistant.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
import kotlinx.android.synthetic.main.alarm_item.view.*
import kotlinx.android.synthetic.main.notifications_item.view.*
import rus.voiceassistant.R
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.view.IAlarmView
import rus.voiceassistant.isDateComesToday
import rus.voiceassistant.model.Notification
import rus.voiceassistant.view.INotificationView
import java.util.*

/**
 * Created by RUS on 10.04.2016.
 */
class NotificationsAdapter(val view: INotificationView, val items: List<Notification>): RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    class ViewHolder(
            val v: View,
            val textTime: TextView = v.textTime,
            val notificationText: TextView = v.notificationText ): RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val context = holder?.itemView?.context

        holder?.textTime?.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)
        holder?.notificationText?.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)

        holder?.textTime?.text = items[position].time
        holder?.notificationText?.text = items[position].text
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder?
            = ViewHolder(LayoutInflater.from(parent?.getContext()).inflate(R.layout.notifications_item, parent, false))

    override fun getItemCount(): Int = items.size

}