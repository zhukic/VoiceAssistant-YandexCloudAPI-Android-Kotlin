package rus.voiceassistant.mvp.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by RUS on 29.03.2016.
 */
public class MyObserver {

    public static final String YANDEX_URL = "https://vins-markup.voicetech.yandex.net/markup/0.x/?text=";
    public static final String LAYERS = "&layers=OriginalRequest,ProcessedRequest,Tokens,Date";
    public static final String API = "&key=8b1a122c-9942-4f0d-a1a6-10a18353131f";

    private ObserveListener observeListener;

    MyObserver(ObserveListener observeListener) {
        this.observeListener = observeListener;
    }

    public void observe(final String text) throws IOException {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(readFromUrl(createUrl(text)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        observeListener.onObserveFinished(s);
                    }
                });
    }

    public String createUrl(String text) {
        try {
            return YANDEX_URL + URLEncoder.encode(text, "UTF-8") + LAYERS + API;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readFromUrl(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            inputLine = inputLine.replace("<![CDATA", "");
            inputLine = inputLine.replace("]>", "");
            inputLine = inputLine.replace("]", "");
            inputLine = inputLine.replace("[", "");
            response.append(inputLine + "\n");
            System.out.println(inputLine);
        }

        in.close();

        return response.toString();
    }

}
