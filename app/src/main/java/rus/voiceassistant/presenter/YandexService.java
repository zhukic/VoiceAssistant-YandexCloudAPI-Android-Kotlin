package rus.voiceassistant.presenter;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rus.voiceassistant.model.Note;

/**
 * Created by RUS on 04.05.2016.
 */
public interface YandexService {

    String LAYERS = "&layers=OriginalRequest,ProcessedRequest,Tokens,Date";

    String API_KEY = "&key=8b1a122c-9942-4f0d-a1a6-10a18353131f";

    String TEXT = "Напомни через 15 минут сделать кофе";

    @GET("?text=" + TEXT + LAYERS + API_KEY)
    Call<ResponseBody> getJSON();

}
