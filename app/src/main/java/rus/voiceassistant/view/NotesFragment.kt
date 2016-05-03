package rus.voiceassistant.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rus.voiceassistant.R

/**
 * Created by RUS on 03.05.2016.
 */
class NotesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.notifications_fragment, container, false)

}