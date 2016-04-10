package rus.voiceassistant.mvp.model.note

/**
 * Created by RUS on 10.04.2016.
 */
class Reminder(var name: String, var time: String, var text: String) {

    fun edit(text: String) {
        this.text = text
    }

}
