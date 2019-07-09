package com.hfad.weatherlive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

public class OneDayActivity extends AppCompatActivity {

    private Forecast forecast;

    public static void start(Context context, Forecast forecast) {
        Intent starter = new Intent(context, OneDayActivity.class);
        starter.putExtra("forecast", forecast);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day);

        forecast = (Forecast) getIntent().getSerializableExtra("forecast");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String date = preferences.getString("date", "01-01-19");
        String desc = preferences.getString("desc", "Sunny");
        String wind = preferences.getString("wind", "10");
        String humidity = preferences.getString("humidity", "50");
        String temp = preferences.getString("temp", "25");
        String icon = preferences.getString("icon", "01d");

        TextView txtDate = (TextView)findViewById(R.id.dayDate);
        TextView txtDesc = (TextView)findViewById(R.id.dayDesc);
        TextView txtWind = (TextView)findViewById(R.id.dayWind);
        TextView txtHumidity = (TextView)findViewById(R.id.dayHumidity);
        TextView txtTemp = (TextView)findViewById(R.id.dayTemp);
        ImageView iconId = (ImageView)findViewById(R.id.dayIcon);

        txtDate.setText(date);
        txtDesc.setText(desc);
        txtWind.setText(wind);
        txtHumidity.setText(humidity);
        txtTemp.setText(temp);
        iconId.setImageResource(getImageResource(icon));

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
