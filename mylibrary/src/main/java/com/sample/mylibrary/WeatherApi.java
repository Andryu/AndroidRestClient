package com.sample.mylibrary;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by shunsuke_andoh on 15/07/25.
 */
public interface WeatherApi {

    @GET("/data/2.5/{name}")
    public Observable<WeatherEntity> get(@Path("name") String name, @Query("q") String q);
    //public Observable<WeatherEntity> getNew(@Path("name") String name, @Query("q") String q);
}
