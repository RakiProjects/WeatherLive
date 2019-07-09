package com.hfad.weatherlive;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ForecastList {

    @SerializedName("list")
    private ArrayList<Forecast> forecastList;

    public ArrayList<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(ArrayList<Forecast> forecastList) {
        this.forecastList = forecastList;
    }
}
