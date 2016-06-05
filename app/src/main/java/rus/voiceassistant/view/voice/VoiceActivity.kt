package rus.voiceassistant.view.voice

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast

import java.util.ArrayList
import java.util.Locale

import ru.yandex.speechkit.SpeechKit
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.Logger
import rus.voiceassistant.R
import rus.voiceassistant.presenter.voice.IVoicePresenter
import rus.voiceassistant.presenter.voice.VoicePresenter
import rus.voiceassistant.toast

class VoiceActivity : Activity(), IVoiceView {

    companion object {
        val REQ_CODE_SPEECH_INPUT = 100
        val API_KEY = "8b1a122c-9942-4f0d-a1a6-10a18353131f"
        val EXTRA_LANGUAGE = "ru-RU"
        val EXTRA_MODEL = "general"
    }

    lateinit var presenter: IVoicePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voice_activity)

        SpeechKit.getInstance().configure(this, API_KEY)

        presenter = VoicePresenter(this)

        yandex()
        //google();

    }

    override fun showToast(text: String) = toast(text)

    override fun finishActivity() = finish()

    override fun getContext(): Context = this

    private fun yandex() {
        val intent = Intent(this, RecognizerActivity::class.java)
        intent.putExtra(RecognizerActivity.EXTRA_LANGUAGE, EXTRA_LANGUAGE)
        intent.putExtra(RecognizerActivity.EXTRA_MODEL, EXTRA_MODEL)
        intent.putExtra(RecognizerActivity.EXTRA_SHOW_PARTIAL_RESULTS, false)
        intent.putExtra(RecognizerActivity.EXTRA_SHOW_RESULTS_LIST, false)
        startActivityForResult(intent, 0)
    }

    private fun google() {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale("ru"))
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "BLA")
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "speech_not_supported",
                    Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
