package rus.voiceassistant.mvp.view;

import android.content.Intent
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.yandex.speechkit.SpeechKit
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.R
import rus.voiceassistant.mvp.presenter.IPresenter
import rus.voiceassistant.mvp.presenter.PresenterImpl


//TODO recyclerView
//TODO tablayout
class MainActivity : AppCompatActivity(), IView {

    companion object {
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

        fab.setOnClickListener({presenter.onRecognitionStarted()});
    }

    override fun startRecognitionActivity() {
        /*val intent = Intent(this, RecognizerActivity::class.java)
        intent.putExtra(RecognizerActivity.EXTRA_LANGUAGE, EXTRA_LANGUAGE)
        intent.putExtra(RecognizerActivity.EXTRA_MODEL, EXTRA_MODEL)
        startActivityForResult(intent, 0)*/
        presenter.observe(editText.getText().toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onRecognitionFinished(requestCode, resultCode, data)
    }

    override fun setText(text: String?) {
        runOnUiThread({textView.text = text})
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
