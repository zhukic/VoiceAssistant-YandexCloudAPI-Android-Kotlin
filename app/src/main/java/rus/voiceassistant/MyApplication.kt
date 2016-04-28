package rus.voiceassistant

import android.app.Application
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.j256.ormlite.dao.Dao
import rus.voiceassistant.database.DatabaseHelper
import rus.voiceassistant.model.Alarm

/**
 * Created by RUS on 15.04.2016.
 */
class MyApplication : Application() {

    companion object {
        var alarmDao: Dao<Alarm, Int>? = null
        var databaseHelper: DatabaseHelper? = null
    }

    override fun onCreate() {
        super.onCreate()

        databaseHelper = getDatabaseHelper()
        alarmDao = databaseHelper?.alarmDAO

    }

    private fun getDatabaseHelper(): DatabaseHelper {
        return OpenHelperManager.getHelper(this, DatabaseHelper::class.java)
    }

    override fun onTerminate() {
        super.onTerminate()

        OpenHelperManager.releaseHelper()
        databaseHelper = null
    }

}