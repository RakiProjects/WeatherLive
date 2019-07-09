//package com.hfad.weatherlive;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//
//
//public class ForecastsFormatter {
//    public ArrayList<Forecast> Read(FileInputStream stream) {
//        ArrayList<Forecast> forecasts = null;
//        try {
//            DataInputStream dataStream = new DataInputStream(stream);
//            forecasts = new ArrayList<Forecast>();
//            int n = dataStream.readInt();
//            for (int i = 0; i < n; i++) {
//                Forecast forecast = new Forecast();
//                forecast.setDate(dataStream.readUTF());
//                forecast.setDescription(dataStream.readUTF());
//                forecast.setIconId(dataStream.readUTF());
//                forecast.setHigh(dataStream.readDouble());
//                forecast.setLow(dataStream.readDouble());
//                forecasts.add(forecast);
//            }
//        }
//        catch (Exception e) {
//
//        }
//        return forecasts;
//    }
//
//    public void Write(FileOutputStream stream,
//                      ArrayList<Forecast> forecasts) {
//        try {
//            DataOutputStream dataStream = new DataOutputStream(stream);
//            dataStream.writeInt(forecasts.size());
//            for (Forecast forecast : forecasts) {
//                dataStream.writeUTF(forecast.getDate());
//                dataStream.writeUTF(forecast.getDescription());
//                dataStream.writeUTF(forecast.getIconId());
//                dataStream.writeDouble(forecast.getHigh());
//                dataStream.writeDouble(forecast.getLow());
//            }
//        }
//        catch (Exception e) {
//        }
//
//    }
//}
