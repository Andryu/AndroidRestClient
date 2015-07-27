package com.sample.mylibrary;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by shunsuke_andoh on 15/07/25.
 */
public class ApiClient {

    private String result;

    /*
    非同期処理
     */
    public void HttpClient() {
        // JSONのパーサー
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        // RestAdapterの生成
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("=NETWORK="))
                .build();


        // 非同期処理の実行
        adapter.create(WeatherApi.class).get("weather", "Tokyo,jp")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", "Error : " + e.toString());
                    }

                    @Override
                    public void onNext(WeatherEntity weather) {
                        Log.d("MainActivity", "onNext()");
                        if (weather != null) {
                            //((TextView) findViewById(R.id.text)).setText(weather.weather.get(0).main);
                            //_result = weather.weather.get(0).main;
                            setResult(weather.weather.get(0).main);
                        }
                    }
                });
    }
    private void setResult(String res){
        result = res;
    }

    public String getResult(){
        return result;
    }
}
