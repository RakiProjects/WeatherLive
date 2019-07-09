package com.hfad.weatherlive;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ForecastArrayAdapter extends ArrayAdapter<Forecast> {

    private ForecastActivity forecastActivity;
    private final ArrayList<Forecast> forecasts;

    public ForecastArrayAdapter(ForecastActivity forecastActivity, ArrayList<Forecast> forecasts){
        super(forecastActivity, R.layout.forecast_item, forecasts);
        this.forecastActivity = forecastActivity;
        this.forecasts = forecasts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = forecastActivity.getLayoutInflater().inflate(R.layout.forecast_item, null);
        }


        TextView date = (TextView)convertView.findViewById(R.id.date);
        TextView desc = (TextView)convertView.findViewById(R.id.desc);
        TextView high = (TextView)convertView.findViewById(R.id.high);
        TextView low = (TextView)convertView.findViewById(R.id.low);
        ImageView iconId = (ImageView)convertView.findViewById(R.id.icon);

        Forecast forecast = forecasts.get(position);

        date.setText(forecast.getDate());
        desc.setText(forecast.getWeather().get(0).getDescription());
        high.setText(Double.toString(forecast.getTemp().getHigh()));
        low.setText(Double.toString(forecast.getTemp().getLow()));
        iconId.setImageResource(getImageResource(forecast.getWeather().get(0).getIconId()));

        return convertView;
    }

    private int getImageResource(String iconId){
        switch (iconId) {
            case "01d":
                return R.drawable.fi01d;
            case "02d":
                return R.drawable.fi02d;
            case "03d":
                return R.drawable.fi03d;
            case "04d":
                return R.drawable.fi04d;
            case "09d":
                return R.drawable.fi09d;
            case "10d":
                return R.drawable.fi10d;
            case "11d":
                return R.drawable.fi11d;
            case "13d":
                return R.drawable.fi13d;
            case "50d":
                return R.drawable.fi50d;
        }
        return  R.drawable.fi01d;
    }
}
