package com.hfad.weatherlive;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastRepository {

    private static ForecastRepository forecastRepository;

    public static ForecastRepository getInstance() {
        if (forecastRepository == null) {
            forecastRepository = new ForecastRepository();
        }
        return forecastRepository;
    }

    private GetForecastDataService service;

    // instanciranje retrofita sa servisom
    private ForecastRepository() {
        service = RetrofitInstance.createService(GetForecastDataService.class);
    }

    public void getForecastList(final MutableLiveData<ForecastResponse> forecastListLiveData) {

        Call<ForecastList> call = service.getForecastData("Belgrade", "json", "metric", "16", "47b0354955ccecc116b7384d51871c39");
        Log.d("URL Called ", call.request().url() + "");

        // asynchronous
        call.enqueue(new Callback<ForecastList>() {
            @Override
            public void onResponse(Call<ForecastList> call, Response<ForecastList> response) {
                if (response.isSuccessful()) {
                    ForecastList forecastList = response.body();
                    forecastListLiveData.setValue(new ForecastResponse(forecastList.getForecastList(), null));
                } else {
                    try {
                        Log.d("QQQ", response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("QQQ", "", e);
                        forecastListLiveData.setValue(new ForecastResponse(null, e));
                    }
                }
            }

            @Override
            public void onFailure(Call<ForecastList> call, Throwable t) {
                forecastListLiveData.setValue(new ForecastResponse(null, t));
                Log.e("QQQ", "usao", t);
            }
        });
    }
}
