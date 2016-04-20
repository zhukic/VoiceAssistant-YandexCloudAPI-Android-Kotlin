package rus.voiceassistant

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
import rus.voiceassistant.mvp.alarm.model.Alarm
import rus.voiceassistant.mvp.alarm.view.IAlarmView
import rus.voiceassistant.isDateComesToday
import java.util.*

/**
 * Created by RUS on 10.04.2016.
 */
class RecyclerAdapter(val view: IAlarmView, val items: List<Alarm>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(
            val v: View,
            val switch: SwitchCompat = v.material_switch,
            val textTime: TextView = v.textTime,
            val textDay: TextView = v.textDay ): RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.textTime?.typeface = Typer.set(view.getContext()).getFont(Font.ROBOTO_REGULAR)
        holder?.textDay?.typeface = Typer.set(view.getContext()).getFont(Font.ROBOTO_REGULAR)

        val calendar = Calendar.getInstance()

        if(calendar.isDateComesToday(items[position].hour, items[position].minute))
            holder?.textDay?.text = "Сегодня"
        else
            holder?.textDay?.text = "Завтра"
        holder?.textTime?.text = items.get(position).getTime()
        holder?.switch?.setOnCheckedChangeListener { button, isChecked -> view.onCheckedChange(position, isChecked) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder?
            = ViewHolder(LayoutInflater.from(parent?.getContext()).inflate(R.layout.alarm_item, parent, false))

    override fun getItemCount(): Int = items.size

}