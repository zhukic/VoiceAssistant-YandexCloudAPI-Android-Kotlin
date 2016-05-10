package rus.voiceassistant.presenter.voice

import rus.voiceassistant.containsWords
import rus.voiceassistant.model.Action
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.model.Notification
import rus.voiceassistant.model.yandex.YandexResponse

/**
 * Created by RUS on 05.05.2016.
 */
class ActionBuilder(val yandexResponse: YandexResponse) {

    var builder: Action.Builder? = null

    fun getAction(): Action? {
        if(!yandexResponse.tokens.filter({ it.text.equals("напомни") }).isEmpty()) {
            builder = Notification.Builder()
            getDate()
            getText()
        } else if(yandexResponse.tokens.containsWords(arrayOf("поставь", "будильник"))) {
            builder = Alarm.Builder()
            getDate()
        } else {

        }
        val action = builder?.build()
        action?.originalRequest = getOriginalRequest()
        return action
    }

    fun getOriginalRequest() = yandexResponse.originalRequest

    private fun getDate() {
        if(!yandexResponse.date.isEmpty()) {
            if(!yandexResponse.date.filter { it.duration != null }.isEmpty()) {
                if(yandexResponse.date.first().duration.type.equals("FORWARD")) {
                    builder!!.minuteForward(yandexResponse.date.first().duration.min)
                            .hourForward(yandexResponse.date.first().duration.hour)
                            .dayForward(yandexResponse.date.first().duration.day)
                            .monthForward(yandexResponse.date.first().duration.month)
                            .yearForward(yandexResponse.date.first().duration.year)
                }
            } else {
                builder!!.minute(yandexResponse.date.first().min)
                        .hour(yandexResponse.date.first().hour)
                        .day(yandexResponse.date.first().day)
                        .month(yandexResponse.date.first().month)
                        .year(yandexResponse.date.first().year)
            }
        }
    }

    private fun getText() {
        var startDateTokenNum = 0;
        var endDateTokenNum = 0
        var text = ""
        if(!yandexResponse.date.isEmpty()) {
            startDateTokenNum = yandexResponse.date.first().tokens.begin
            endDateTokenNum = yandexResponse.date.first().tokens.end
        }
        yandexResponse.tokens.filterIndexed { index, token -> !(index >= startDateTokenNum && index < endDateTokenNum) }.forEach { text += "${it.text} "}
        if(builder is Notification.Builder) (builder as Notification.Builder).text(text)
    }
}