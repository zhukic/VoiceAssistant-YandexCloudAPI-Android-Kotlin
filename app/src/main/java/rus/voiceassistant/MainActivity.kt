package rus.voiceassistant;

import android.app.FragmentManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle;
import android.provider.AlarmClock
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment
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
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import ru.yandex.speechkit.SpeechKit
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.Logger
import rus.voiceassistant.R
import rus.voiceassistant.model.Alarm
import rus.voiceassistant.view.alarm.AlarmsFragment
import rus.voiceassistant.addFragment
import rus.voiceassistant.replaceFragment
import rus.voiceassistant.view.note.NotesFragment
import rus.voiceassistant.view.notification.NotificationsFragment
import java.util.*

//TODO stickyListHeader
//TODO alarm
//TODO custom spinner
class MainActivity : AppCompatActivity() {

    companion object {

        val TAG = javaClass.simpleName

        val API_KEY = "8b1a122c-9942-4f0d-a1a6-10a18353131f"
    }

    lateinit var currentFragment: Fragment
    lateinit var drawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SpeechKit.getInstance().configure(this, API_KEY)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initNavigationDrawer()

        currentFragment = NotificationsFragment()

        drawer.setSelectionAtPosition(2)

        supportFragmentManager.addFragment(currentFragment)

        //fab.setOnClickListener({presenter.onRecognitionStarted()});
    }

    fun initNavigationDrawer() {

        val accountHeader = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .build()

        drawer = DrawerBuilder()
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
                .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*, *>?): Boolean {
                        when(position) {
                            1 -> {
                                if(currentFragment !is AlarmsFragment) {
                                    currentFragment = AlarmsFragment()
                                    supportFragmentManager.replaceFragment(currentFragment )
                                }
                            }

                            2 -> {
                                if(currentFragment !is NotificationsFragment) {
                                    currentFragment = NotificationsFragment()
                                    supportFragmentManager.replaceFragment(currentFragment)
                                }
                            }

                            3 -> {
                                if(currentFragment !is NotesFragment) {
                                    currentFragment = NotesFragment()
                                    supportFragmentManager.replaceFragment(currentFragment)
                                }
                            }
                        }
                        drawer.closeDrawer()
                        return true
                    }
                })
                .withCloseOnClick(true)
                .build()
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
    }

}
