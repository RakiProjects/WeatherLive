package com.hfad.weatherlive;

import android.content.Context;

import java.util.ArrayList;

public abstract class DataCaching {
    private Context context;

    protected Context getContext() {
        return context;
    }

    public DataCaching(Context context) {
        this.context = context;
    }

    public abstract ArrayList<Forecast> readForecasts(long locationId) throws Exception;

    public abstract void writeForecasts(long locationId, ArrayList<Forecast> forecasts) throws Exception;
}
