package rus.voiceassistant.view.adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
import kotlinx.android.synthetic.main.alarm_item.view.*
import rus.voiceassistant.R
import rus.voiceassistant.isDateComesToday
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.view.alarm.IAlarmView
import rus.voiceassistant.view.IView
import java.util.*

/**
 * Created by RUS on 28.04.2016.
 */
class AlarmsAdapter(val view: IAlarmView, val items: List<Alarm>): RecyclerView.Adapter<AlarmsAdapter.ViewHolder>() {

    class ViewHolder(
            val v: View,
            val switch: SwitchCompat = v.material_switch,
            val textTime: TextView = v.alarmTime,
            val textDay: TextView = v.textDay ): RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val context = holder?.itemView?.context

        holder?.textTime?.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)
        holder?.textDay?.typeface = Typer.set(context).getFont(Font.ROBOTO_REGULAR)

        val calendar = Calendar.getInstance()

        holder?.textDay?.text = if(calendar.isDateComesToday(items[position].hour, items[position].minute)) "Сегодня" else "Завтра"
        //holder?.textTime?.text = items.get(position).getTime()
        holder?.switch?.setOnCheckedChangeListener { button, isChecked -> view.onCheckedChange(position, isChecked) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder?
            = ViewHolder(LayoutInflater.from(parent?.getContext()).inflate(R.layout.alarm_item, parent, false))

    override fun getItemCount(): Int = items.size

}