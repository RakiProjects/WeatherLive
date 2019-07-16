package com.hfad.weatherlive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Forecast implements Serializable {


    @SerializedName("dt")
    @Expose
    private String date;

    @SerializedName("speed")
    @Expose
    private double windSpeed;

    @SerializedName("humidity")
    @Expose
    private int humidity;

    @SerializedName("temp")
    @Expose
    private Temp temp;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;

    public Forecast() {
    }

    public Forecast(String date, double windSpeed, int humidity, Temp temp, List<Weather> weather) {
        this.date = date;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.temp = temp;
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather>weather) {
        this.weather = weather;
    }

}
