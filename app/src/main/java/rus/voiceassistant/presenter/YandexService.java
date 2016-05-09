package rus.voiceassistant.presenter;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rus.voiceassistant.model.yandex.YandexResponse;

/**
 * Created by RUS on 04.05.2016.
 */
public interface YandexService {

    @GET("markup/0.x/")
    Call<YandexResponse> getJsonResponse(@Query("text") String text, @Query("layers") String layers, @Query("key") String key);

}
