package com.example.fernalia.simpleappwithretrofit.rest;

/**
 * Created by Fernalia on 23/08/2017.
 */

import com.example.fernalia.simpleappwithretrofit.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  ApiInterface {
    @GET("staticweather")
    Call<Forcast> getWeatherForcast(@Query("q") String q,
                                    @Query("mode") String mode,
                                    @Query("units") String units,
                                    @Query("cnt") int cnt);
}
