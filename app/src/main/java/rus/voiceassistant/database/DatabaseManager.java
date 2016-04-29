package rus.voiceassistant.database;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import rus.voiceassistant.MyApplication;
import rus.voiceassistant.model.Alarm;
import rus.voiceassistant.model.Notification;

/**
 * Created by RUS on 15.04.2016.
 */
public class DatabaseManager {

    public static ArrayList<Alarm> getAlarmsListFromDatabase() {

        ArrayList<Alarm> currentNewsList = new ArrayList<>();
        try {
            QueryBuilder<Alarm, Integer> queryBuilder = MyApplication.Companion.getAlarmDao().queryBuilder();
            currentNewsList = (ArrayList<Alarm>)MyApplication.Companion.getAlarmDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.reverse(currentNewsList);
        return currentNewsList;
    }

    public static ArrayList<Notification> getNotificationsListFromDatabase() {

        ArrayList<Notification> currentNewsList = new ArrayList<>();
        try {
            QueryBuilder<Notification, Integer> queryBuilder = MyApplication.Companion.getNotificationDao().queryBuilder();
            currentNewsList = (ArrayList<Notification>)MyApplication.Companion.getNotificationDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.reverse(currentNewsList);
        return currentNewsList;
    }

    public static void removeAlarm(int id) {
        try {
            MyApplication.Companion.getAlarmDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeNotification(int id) {
        try {
            MyApplication.Companion.getNotificationDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
