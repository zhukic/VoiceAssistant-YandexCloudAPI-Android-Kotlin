package rus.voiceassistant.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.notes_fragment.*
import ru.yandex.speechkit.gui.RecognizerActivity
import rus.voiceassistant.R
import rus.voiceassistant.main.view.MainActivity
import rus.voiceassistant.presenter.INotePresenter
import rus.voiceassistant.presenter.NotePresenter

/**
 * Created by RUS on 03.05.2016.
 */
class NotesFragment : Fragment(), INoteView {

    lateinit var presenter: INotePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.notes_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        presenter = NotePresenter(this)
        fab.setOnClickListener { presenter.onRecognitionStarted() }
    }

    override fun startRecognitionActivity() {
        val intent = Intent(activity, RecognizerActivity::class.java)
        intent.putExtra(RecognizerActivity.EXTRA_LANGUAGE, MainActivity.EXTRA_LANGUAGE)
        intent.putExtra(RecognizerActivity.EXTRA_MODEL, MainActivity.EXTRA_MODEL)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onRecognitionFinished(requestCode, resultCode, data)
    }

    override fun onError() {
        textView.text = "ERROR"
    }

}