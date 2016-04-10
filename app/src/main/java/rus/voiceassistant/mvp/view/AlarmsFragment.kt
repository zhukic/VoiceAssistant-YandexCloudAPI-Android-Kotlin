package rus.voiceassistant.mvp.view

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rus.voiceassistant.R

/**
 * Created by RUS on 10.04.2016.
 */
class AlarmsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.alarm_fragment, container, false)



        return view
    }

}