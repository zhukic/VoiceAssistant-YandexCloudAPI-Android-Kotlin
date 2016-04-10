package rus.voiceassistant

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by RUS on 10.04.2016.
 */
class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder: RecyclerView.ViewHolder {

        constructor(itemView: View?) : super(itemView) {

        }

    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return null
    }

    override fun getItemCount(): Int {
        return 0
    }

}