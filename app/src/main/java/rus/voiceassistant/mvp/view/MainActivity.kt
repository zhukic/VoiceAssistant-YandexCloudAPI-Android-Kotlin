package rus.voiceassistant.mvp.view;

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
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.yandex.speechkit.SpeechKit
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.R
import rus.voiceassistant.mvp.presenter.IPresenter
import rus.voiceassistant.mvp.presenter.PresenterImpl


//TODO recyclerView
//TODO tablayout
//TODO timePicker
//TODO builder для будильника
class MainActivity : AppCompatActivity(), IView {

    companion object {

        val TAG = "TAG"

        val EXTRA_LANGUAGE = "ru-RU"
        val EXTRA_MODEL = "general"
    }

    lateinit var presenter: IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SpeechKit.getInstance().configure(this, "8b1a122c-9942-4f0d-a1a6-10a18353131f")

        presenter = PresenterImpl(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initNavigationDrawer()

        fab.setOnClickListener({presenter.onRecognitionStarted()});
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

    override fun setText(text: String?) {
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
                                .withTypeface(Typeface.createFromAsset(assets, "Roboto-Medium.ttf"))
                                .withIcon(R.mipmap.ic_alarm)
                                .withIconTintingEnabled(true)
                                .withSelectedIconColorRes(R.color.colorPrimary),
                        PrimaryDrawerItem()
                                .withName(getString(R.string.action_reminder))
                                .withIdentifier(2)
                                .withTextColor(Color.BLACK)
                                .withSelectedTextColorRes(R.color.colorPrimary)
                                .withTypeface(Typeface.createFromAsset(assets, "Roboto-Medium.ttf"))
                                .withIcon(R.mipmap.ic_exclamation)
                                .withIconTintingEnabled(true)
                                .withSelectedIconColorRes(R.color.colorPrimary),
                        PrimaryDrawerItem()
                                .withName(getString(R.string.action_note))
                                .withIdentifier(3)
                                .withTextColor(Color.BLACK)
                                .withSelectedTextColorRes(R.color.colorPrimary)
                                .withTypeface(Typeface.createFromAsset(assets, "Roboto-Medium.ttf"))
                                .withIcon(R.mipmap.ic_note)
                                .withIconTintingEnabled(true)
                                .withSelectedIconColorRes(R.color.colorPrimary),
                        DividerDrawerItem(),
                        PrimaryDrawerItem()
                                .withName(getString(R.string.action_settings))
                                .withIdentifier(1)
                                .withTextColor(Color.BLACK)
                                .withSelectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                                .withTypeface(Typeface.createFromAsset(assets, "Roboto-Medium.ttf"))
                                .withIcon(R.mipmap.ic_settings)
                                .withIconTintingEnabled(true)
                                .withSelectedIconColorRes(R.color.colorPrimary)
                )
                .withOnDrawerItemClickListener { view, i, iDrawerItem -> createAlarm(i) }
                .build()
    }

    fun createAlarm(i: Int): Boolean {
        Log.i(TAG, i.toString())
        if(i == 1) {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_HOUR, 7)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, 30)
            intent.putExtra(AlarmClock.EXTRA_IS_PM, true)
            startActivity(intent)
        }
        return true
    }

}
