package rus.voiceassistant.mvp.main.view;

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle;
import android.provider.AlarmClock
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log
import android.view.Menu;
import android.view.MenuItem;
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.wdullaer.materialdatetimepicker.Utils;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.holder.BadgeStyle
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fab_menu.*
import ru.yandex.speechkit.SpeechKit
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.R
import rus.voiceassistant.mvp.alarm.model.Alarm
import rus.voiceassistant.mvp.alarm.view.AlarmsFragment
import rus.voiceassistant.mvp.main.presenter.IPresenter
import rus.voiceassistant.mvp.main.presenter.PresenterImpl
import java.util.*



class MainActivity : AppCompatActivity(), IView {

    companion object {

        val TAG = "TAG"

        val API_KEY = "8b1a122c-9942-4f0d-a1a6-10a18353131f"
        val EXTRA_LANGUAGE = "ru-RU"
        val EXTRA_MODEL = "general"
    }

    lateinit var presenter: IPresenter
    lateinit var alarmsFragment: AlarmsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SpeechKit.getInstance().configure(this, API_KEY)

        presenter = PresenterImpl(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initNavigationDrawer()

        alarmsFragment = AlarmsFragment()

        fragmentManager.beginTransaction().add(R.id.fragment_container, alarmsFragment).commit()

        fab_menu.setClosedOnTouchOutside(true)

        fab_micro.setOnClickListener { presenter.onRecognitionStarted() }
        fab_create_alarm.setOnClickListener { presenter.onCreateAlarmClicked() }
        fab_create_remind.setOnClickListener { presenter.onCreateNotificationClicked() }
        //fab.setOnClickListener({presenter.onRecognitionStarted()});
    }

    fun initNavigationDrawer() {

        val accountHeader = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .build()

        DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(accountHeader)
                .withToolbar(toolbar)
                .addDrawerItems(
                        PrimaryDrawerItem()
                                .withName(getString(R.string.action_alarm))
                                .withIdentifier(1)
                                .withTextColor(Color.BLACK)
                                .withSelectedTextColorRes(R.color.colorPrimary)
                                .withTypeface(Typer.set(this).getFont(Font.ROBOTO_MEDIUM))
                                .withIcon(GoogleMaterial.Icon.gmd_access_alarms),
                        PrimaryDrawerItem()
                                .withName(getString(R.string.action_reminder))
                                .withIdentifier(2)
                                .withTextColor(Color.BLACK)
                                .withSelectedTextColorRes(R.color.colorPrimary)
                                .withTypeface(Typer.set(this).getFont(Font.ROBOTO_MEDIUM))
                                .withIcon(GoogleMaterial.Icon.gmd_event),
                        PrimaryDrawerItem()
                                .withName(getString(R.string.action_note))
                                .withIdentifier(3)
                                .withTextColor(Color.BLACK)
                                .withSelectedTextColorRes(R.color.colorPrimary)
                                .withTypeface(Typer.set(this).getFont(Font.ROBOTO_MEDIUM))
                                .withIcon(CommunityMaterial.Icon.cmd_note_outline),
                        DividerDrawerItem(),
                        PrimaryDrawerItem()
                                .withName(getString(R.string.action_settings))
                                .withIdentifier(4)
                                .withTextColor(Color.BLACK)
                                .withSelectedTextColorRes(R.color.colorPrimary)
                                .withTypeface(Typer.set(this).getFont(Font.ROBOTO_MEDIUM))
                                .withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                //.withOnDrawerItemClickListener { view, i, iDrawerItem -> createAlarm(i) }
                .withCloseOnClick(true)
                .build()
    }

    override fun startRecognitionActivity() {
        val intent = Intent(this, RecognizerActivity::class.java)
        intent.putExtra(RecognizerActivity.EXTRA_LANGUAGE, EXTRA_LANGUAGE)
        intent.putExtra(RecognizerActivity.EXTRA_MODEL, EXTRA_MODEL)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //presenter.onRecognitionFinished(requestCode, resultCode, data)
    }

    override fun showTimePicker() {
        val tpd: TimePickerDialog = TimePickerDialog.newInstance(this, 0, 0, true);
        tpd.show(getFragmentManager(), "TimePickerDialog");
    }

    override fun showDatePicker() {
        val calendar: Calendar = Calendar.getInstance(Locale("ru"))
        val dpd = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
        dpd.show(fragmentManager, "DatePicker")
    }

    override fun onTimeSet(view: RadialPickerLayout?, hourOfDay: Int, minute: Int, second: Int) {
        alarmsFragment.addAlarm(Alarm(hourOfDay, minute))
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
    }

    override fun onError() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if(id == R.id.action_settings) return true

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun createAlarm(i: Int): Boolean {
        Log.i(TAG, i.toString())
        if(i == 100) {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_HOUR, 7)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, 30)
            intent.putExtra(AlarmClock.EXTRA_IS_PM, true)
        }
        return true
    }

}
