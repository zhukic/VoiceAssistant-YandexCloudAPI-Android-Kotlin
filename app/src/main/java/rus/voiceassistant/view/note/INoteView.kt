package rus.voiceassistant.view.note

import rus.voiceassistant.model.Notification

/**
 * Created by RUS on 04.05.2016.
 */
interface INoteView {

    fun startRecognitionActivity()

    fun onError()

    fun createNotification(notification: Notification)

}