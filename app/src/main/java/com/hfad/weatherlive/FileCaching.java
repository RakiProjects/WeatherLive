//package com.hfad.weatherlive;
//
//import android.content.Context;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//
//
//public class FileCaching extends DataCaching {
//    public FileCaching(Context context) {
//        super(context);
//    }
//
//    @Override
//    public ArrayList<Forecast> readForecasts(long locationId) throws Exception {
//        File file = new File(getContext().getCacheDir(),
//                Long.toString(locationId) + ".tmp");
//        ArrayList<Forecast> forecasts = new ArrayList<Forecast>();
//        if (file.exists()) {
//            FileInputStream stream = new FileInputStream(file);
//            ForecastsFormatter formatter = new ForecastsFormatter();
//            forecasts = formatter.Read(stream);
//        }
//        return forecasts;
//    }
//
//    @Override
//    public void writeForecasts(long locationId, ArrayList<Forecast> forecasts) throws Exception {
//        File file = new File(getContext().getCacheDir(),
//                Long.toString(locationId)+".tmp");
//        FileOutputStream stream = new FileOutputStream(file);
//        ForecastsFormatter forecastsFormatter = new ForecastsFormatter();
//        forecastsFormatter.Write(stream, forecasts);
//        stream.close();
//    }
//}
