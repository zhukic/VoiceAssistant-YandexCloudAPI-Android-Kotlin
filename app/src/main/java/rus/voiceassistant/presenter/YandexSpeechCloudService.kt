package rus.voiceassistant.presenter

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by RUS on 04.05.2016.
 */
interface YandexSpeechCloudService {

    companion object {
        val LAYERS = "&layers=OriginalRequest,ProcessedRequest,Tokens,Date"
        val API = "&key=8b1a122c-9942-4f0d-a1a6-10a18353131f"
    }
}