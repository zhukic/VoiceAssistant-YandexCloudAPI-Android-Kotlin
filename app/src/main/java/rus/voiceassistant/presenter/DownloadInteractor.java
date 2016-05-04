package rus.voiceassistant.presenter;

import android.util.Log;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rus.voiceassistant.Logger;

/**
 * Created by RUS on 04.05.2016.
 */
class DownloadInteractor implements IDownloadInteractor {

    public static final String  BASE_URL = "https://vins-markup.voicetech.yandex.net/markup/0.x/";

    private YandexService yandexService;

    DownloadInteractor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        yandexService = retrofit.create(YandexService.class);
    }

    @Override
    public void downloadJson(@NotNull String text, @NotNull final OnFinishedListener onFinishedListener) {

        Call<ResponseBody> call = yandexService.getJSON();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Logger.Companion.log(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.Companion.log("Error");
            }
        });
    }
}