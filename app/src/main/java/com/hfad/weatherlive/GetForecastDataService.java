package com.hfad.weatherlive;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetForecastDataService {

    @GET("data/2.5/forecast/daily")
    Call<ForecastList> getForecastData(@Query("q") String city, @Query("mode") String mode, @Query("units") String units, @Query("cnt") String cnt, @Query("APPID") String key);
}
