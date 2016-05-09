package rus.voiceassistant

import android.app.Application
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.table.TableUtils
import rus.voiceassistant.database.DatabaseHelper
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.model.Notification

/**
 * Created by RUS on 15.04.2016.
 */
class MyApplication : Application() {

    companion object {
        lateinit var databaseHelper: DatabaseHelper
        lateinit var alarmDao: Dao<Alarm, Int>
        lateinit var notificationDao: Dao<Notification, Int>
    }

    override fun onCreate() {
        super.onCreate()

        databaseHelper = getDatabaseHelper()

        alarmDao = databaseHelper.alarmDAO
        notificationDao = databaseHelper.notificationDAO

    }

    private fun getDatabaseHelper(): DatabaseHelper {
        return OpenHelperManager.getHelper(this, DatabaseHelper::class.java)
    }

    override fun onTerminate() {
        super.onTerminate()

        OpenHelperManager.releaseHelper()
        //databaseHelper = null
    }

}