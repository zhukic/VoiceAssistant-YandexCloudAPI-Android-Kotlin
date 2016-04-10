package rus.voiceassistant

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import kotlinx.android.synthetic.main.alarm_item.view.*
import rus.voiceassistant.mvp.model.alarm.Alarm

/**
 * Created by RUS on 10.04.2016.
 */
class RecyclerAdapter(val items: List<Alarm>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(
            val v: View,
            val checkBox: CheckBox = v.checkbox,
            val textTime: TextView = v.textTime,
            val textDay: TextView = v.textDay ): RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textTime?.text = items.get(position).hour.toString() + ":" + items.get(position).minute.toString()
        holder?.textDay?.text = "Text"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder?
            = ViewHolder(LayoutInflater.from(parent?.getContext()).inflate(R.layout.alarm_item, parent, false))

    override fun getItemCount(): Int = items.size

}