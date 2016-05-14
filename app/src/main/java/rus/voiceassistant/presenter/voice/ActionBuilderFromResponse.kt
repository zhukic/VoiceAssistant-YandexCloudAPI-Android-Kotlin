package rus.voiceassistant.presenter.voice

import rus.voiceassistant.Logger
import rus.voiceassistant.startsWithAtLeastOneWordFromArray
import rus.voiceassistant.model.actions.Action
import rus.voiceassistant.model.actions.Alarm
import rus.voiceassistant.model.actions.Notification
import rus.voiceassistant.model.builders.ActionBuilder
import rus.voiceassistant.model.builders.AlarmBuilder
import rus.voiceassistant.model.builders.NotificationBuilder
import rus.voiceassistant.model.yandex.GoogleSearchAction
import rus.voiceassistant.model.yandex.YandexResponse
import rus.voiceassistant.startsWithArraysWords

/**
 * Created by RUS on 05.05.2016.
 */
class ActionBuilderFromResponse(val yandexResponse: YandexResponse) {

    companion object {
        val REMIND_WORDS: Array<String> = arrayOf("запомни", "наполни", "напомни", "напомнит", "напомнить", "наполнитель", "напомнил")
        val SET_WORDS: Array<String> = arrayOf("поставь", "поставить")
        val ALARM_WORDS: Array<String> = arrayOf("будильник")
        val FIND_WORDS: Array<String> = arrayOf("найди", "найти")
        val IN_WORDS: Array<String> = arrayOf("в")
        val INTERNET_WORDS: Array<String> = arrayOf("интернете", "интернет")
    }

    var builder: ActionBuilder? = null

    fun getAction(): Action? {
        var action: Action? = null
        if(yandexResponse.tokens.startsWithAtLeastOneWordFromArray(REMIND_WORDS)) {
            yandexResponse.tokens.removeAt(0)
            Logger.log("true")
            builder = NotificationBuilder()
            getDate()
            getText()
            action = builder?.build()
        } else if(yandexResponse.tokens.startsWithArraysWords(arrayOf(SET_WORDS, ALARM_WORDS))) {
            yandexResponse.tokens.removeAt(0)
            yandexResponse.tokens.removeAt(0)
            builder = AlarmBuilder()
            getDate()
            action = builder?.build()
        } else if(yandexResponse.tokens.startsWithArraysWords(arrayOf(FIND_WORDS, IN_WORDS, INTERNET_WORDS))) {
            yandexResponse.tokens.removeAt(0)
            yandexResponse.tokens.removeAt(0)
            yandexResponse.tokens.removeAt(0)
            action = GoogleSearchAction(getText())
        }

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
                builder!!.second(0)
                        .minute(yandexResponse.date.first().min)
                        .hour(yandexResponse.date.first().hour)
                        .day(yandexResponse.date.first().day)
                        .month(yandexResponse.date.first().month)
                        .year(yandexResponse.date.first().year)
            }
        }
    }

    private fun getText(): String {
        var startDateTokenNum = 0;
        var endDateTokenNum = 0
        var text = ""
        if(!yandexResponse.date.isEmpty()) {
            startDateTokenNum = yandexResponse.date.first().tokens.begin
            endDateTokenNum = yandexResponse.date.first().tokens.end
        }
        yandexResponse.tokens.filterIndexed { index, token -> !(index >= startDateTokenNum && index < endDateTokenNum) }.forEach { text += "${it.text} "}
        if(builder is NotificationBuilder) (builder as NotificationBuilder).text(text)
        return text
    }
}
