package rus.voiceassistant.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by RUS on 28.04.2016.
 */
@DatabaseTable(tableName = "notifications")
class Notification {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var time: String = ""

    @DatabaseField
    var text: String = ""

    constructor() {}
}