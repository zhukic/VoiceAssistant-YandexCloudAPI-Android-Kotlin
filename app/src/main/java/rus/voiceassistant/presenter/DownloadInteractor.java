package rus.voiceassistant.presenter;

import android.util.Log;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rus.voiceassistant.Logger;
import rus.voiceassistant.model.yandex.Example;

/**
 * Created by RUS on 04.05.2016.
 */
class DownloadInteractor implements IDownloadInteractor {

    public static final String  BASE_URL = "https://vins-markup.voicetech.yandex.net/markup/0.x/";

    private YandexService yandexService;

    DownloadInteractor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        yandexService = retrofit.create(YandexService.class);
    }

    @Override
    public void downloadJson(@NotNull String text, @NotNull final OnFinishedListener onFinishedListener) {

        Call<Example> call = yandexService.getJsonResponse();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                    Logger.Companion.log(response.body().toString());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Logger.Companion.log("Error");
            }
        });
    }
}