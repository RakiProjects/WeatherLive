package com.hfad.weatherlive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {


    @SerializedName("max")
    @Expose
    private double high;

    @SerializedName("min")
    @Expose
    private double low;

    @SerializedName("day")
    @Expose
    private double day;

    public Temp(double high, double low, double day) {
        this.high = high;
        this.low = low;
        this.day = day;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getTemp() {
        return day;
    }

    public void setTemp(double day) {
        this.day = day;
    }
}
