package rus.voiceassistant.model.actions

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable

/**
 * Created by RUS on 28.04.2016.
 */
@DatabaseTable(tableName = "books")
class Book(@DatabaseField var author: String = "", @DatabaseField var name: String = "") : Comparable<Book>, Serializable {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var isRead: Boolean = false

    override fun compareTo(other: Book): Int = this.isRead.compareTo(other.isRead) * (-1)

}