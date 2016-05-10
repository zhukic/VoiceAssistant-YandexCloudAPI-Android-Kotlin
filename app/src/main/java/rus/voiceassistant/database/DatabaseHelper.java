package rus.voiceassistant.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import rus.voiceassistant.model.Alarm;
import rus.voiceassistant.model.Notification;

/**
 * Created by RUS on 15.04.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME ="NEWS_DATABASE7.db";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<Alarm, Integer> alarmDao = null;
    private Dao<Notification, Integer> notificationDao = null;

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, Alarm.class);
            TableUtils.createTable(connectionSource, Notification.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer){
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Alarm.class, true);
            TableUtils.dropTable(connectionSource, Notification.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */

    public Dao<Alarm, Integer> getAlarmDAO() throws SQLException {
        if(alarmDao == null){
            try {
                alarmDao = getDao(Alarm.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return alarmDao;
    }

    public Dao<Notification, Integer> getNotificationDAO() throws SQLException {
        if(notificationDao == null){
            try {
                notificationDao = getDao(Notification.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return notificationDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */

    @Override
    public void close(){
        super.close();
        alarmDao = null;
        notificationDao = null;
    }
}
