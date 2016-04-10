package rus.voiceassistant.mvp.view

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.alarm_fragment.*
import rus.voiceassistant.R
import rus.voiceassistant.RecyclerAdapter
import rus.voiceassistant.mvp.model.alarm.Alarm
import java.util.*

/**
 * Created by RUS on 10.04.2016.
 */
class AlarmsFragment: Fragment() {

    val alarms = ArrayList<Alarm>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.alarm_fragment, container, false)
        val recyclerView = view?.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(getActivity())
        recyclerView.adapter = RecyclerAdapter(alarms)

        return view
    }

    fun addAlarm(alarm: Alarm) {
        alarms.add(alarm)
        recyclerView.adapter.notifyItemInserted(alarms.size - 1)
    }
}