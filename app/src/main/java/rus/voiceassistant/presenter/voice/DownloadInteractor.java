package rus.voiceassistant.presenter.voice;


import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rus.voiceassistant.model.yandex.YandexResponse;

/**
 * Created by RUS on 04.05.2016.
 */
public class DownloadInteractor implements IDownloadInteractor {

    public static final String BASE_URL = "https://vins-markup.voicetech.yandex.net/";
    public static final String LAYERS = "OriginalRequest,ProcessedRequest,Tokens,Date";
    public static final String API_KEY = "8b1a122c-9942-4f0d-a1a6-10a18353131f";

    private YandexService yandexService;

    public DownloadInteractor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        yandexService = retrofit.create(YandexService.class);
    }

    public void makeRequestToYandexServer(@NotNull final String text, @NotNull final OnFinishedListener onFinishedListener) {

        Call<YandexResponse> call = yandexService.getJsonResponse(text, LAYERS, API_KEY);
        call.enqueue(new Callback<YandexResponse>() {

            public void onResponse(Call<YandexResponse> call, Response<YandexResponse> response) {
                onFinishedListener.onDownloadFinished(response.body());
            }

            public void onFailure(Call<YandexResponse> call, Throwable t) {

            }
        });
    }
}