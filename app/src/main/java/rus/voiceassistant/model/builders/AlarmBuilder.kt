package rus.voiceassistant.model.builders

import rus.voiceassistant.model.actions.Action
import rus.voiceassistant.model.actions.Alarm

/**
 * Created by RUS on 14.05.2016.
 */
class AlarmBuilder: ActionBuilder {

    constructor() : super()

    constructor(alarm: Alarm) : super(alarm)

    override fun build(): Action = Alarm(this)
}