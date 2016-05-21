package rus.voiceassistant.presenter.voice


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rus.voiceassistant.model.yandex.YandexResponse

/**
 * Created by RUS on 04.05.2016.
 */
class DownloadInteractor : IDownloadInteractor {

    companion object {

        val BASE_URL = "https://vins-markup.voicetech.yandex.net/"
        val LAYERS = "OriginalRequest,ProcessedRequest,Tokens,Date"
        val API_KEY = "8b1a122c-9942-4f0d-a1a6-10a18353131f"
    }

    private val yandexService: YandexService

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        yandexService = retrofit.create(YandexService::class.java)
    }

    override fun makeRequestToYandexServer(text: String, onFinishedListener: IDownloadInteractor.OnFinishedListener) {

        val call = yandexService.getJsonResponse(text, LAYERS, API_KEY)
        call.enqueue(object : Callback<YandexResponse> {

            override fun onResponse(call: Call<YandexResponse>, response: Response<YandexResponse>) {
                onFinishedListener.onDownloadFinished(response.body())
            }

            override fun onFailure(call: Call<YandexResponse>, t: Throwable) {

            }
        })
    }
}