package com.hfad.weatherlive;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForecastViewModel extends ViewModel {

    public MutableLiveData<ForecastResponse> forecastListLiveData = new MutableLiveData<>();
    private ForecastRepository forecastRepository;


    public void getForecastList(){
        if(forecastListLiveData.getValue() != null) {
            return;
        }
        // pravi(singleton) i vrati instancu repozitorijuma gde se u konstruktoru poziva retrofit
        forecastRepository = ForecastRepository.getInstance();
        // metod koji dohvata JSON podatke i u LiveData promenljivoj, koja se observuje, setujemo value
        forecastRepository.getForecastList(forecastListLiveData);
    }
}
