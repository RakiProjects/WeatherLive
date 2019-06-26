package com.hfad.weatherlive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ForecastActivity extends AppCompatActivity {


    private static final String OWM_LIST = "list";
    private ListView listViewForecast;
    private ArrayAdapter<Forecast> forecastArrayAdapter;
    private ArrayList<Forecast> forecasts = new ArrayList<Forecast>();
    private int lat, lon;
    public String cityName;

    private FileCaching fileCaching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        updateForecast();
        fileCaching = new FileCaching(ForecastActivity.this);
        for (int a = 0; a < 3; a++) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }

        if (forecasts.size() > 0) {
            listViewForecast = (ListView) findViewById(R.id.listView);
            forecastArrayAdapter = new ForecastArrayAdapter(this, forecasts);
            listViewForecast.setAdapter(forecastArrayAdapter);

            final TextView cityName = (TextView) findViewById(R.id.cityName);
            cityName.setText(this.cityName);

            Log.v("AAA", "USAO U IF");

            listViewForecast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ForecastActivity.this, OneDayActivity.class);
                    Forecast forecast = new Forecast();
                    forecast = forecasts.get(position);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ForecastActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("date", forecast.getDate());
                    editor.putString("desc", forecast.getDescription());
                    editor.putString("wind", String.valueOf(forecast.getWindSpeed()));
                    editor.putString("humidity", String.valueOf(forecast.getHumidity()));
                    editor.putString("temp", String.valueOf(forecast.getTemp()));
                    editor.putString("icon", String.valueOf(forecast.getIconId()));
                    editor.commit();
                    startActivity(intent);
                }
            });
        } else {
            Log.v("AAA", "USAO U ELSE");
        }

    }

    private void updateForecast() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ForecastActivity.this);
        String lat = preferences.getString("Latitude", "0");
        String lon = preferences.getString("Longitude", "0");

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                try {
                    Uri.Builder uriBuilder = new Uri.Builder();
                    uriBuilder.encodedPath("http://api.openweathermap.org/data/2.5/forecast/daily");
                    uriBuilder.appendQueryParameter("lat", params[0]);
                    uriBuilder.appendQueryParameter("lon", params[1]);
                    uriBuilder.appendQueryParameter("mode", "json");
                    uriBuilder.appendQueryParameter("units", "metric");
                    uriBuilder.appendQueryParameter("cnt", "8");
                    uriBuilder.appendQueryParameter("APPID", "47b0354955ccecc116b7384d51871c39");
                    URL url = new URL(uriBuilder.build().toString());

                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null)
                        sb.append(line + "\n");
                    Log.v("JSON", sb.toString());
                    forecasts.addAll(parseJsonForecasts(sb.toString()));

                    if (forecasts.size() > 0) {
                        fileCaching.writeForecasts(1, forecasts);
                    }else {
                        forecasts.clear();
                        ArrayList<Forecast> newForecasts = fileCaching.readForecasts(1);
                        forecasts.addAll(newForecasts);
                    }

                } catch (Exception e) {
                    Log.e("Exception", e.toString());

                } finally {
                    if (httpURLConnection != null)
                        httpURLConnection.disconnect();
                    if (bufferedReader != null)
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            Log.e("Exception", e.toString());
                        }
                }
                return null;
            }
        }.execute(lat, lon);
    }

    private ArrayList<Forecast> parseJsonForecasts(String s) throws JSONException {
        JSONObject jsonRoot = new JSONObject(s);


        JSONObject jsonCityName = jsonRoot.getJSONObject("city");
        cityName = jsonCityName.getString("name");


        JSONArray jsonForecasts = jsonRoot.getJSONArray(OWM_LIST);
        ArrayList<Forecast> forecasts = new ArrayList<Forecast>();
        for (int i = 0; i < jsonForecasts.length(); i++) {
            JSONObject jsonForecast = jsonForecasts.getJSONObject(i);
            Forecast forecast = new Forecast();

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(jsonForecast.getLong("dt") * 1000);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
            String date = format.format(cal.getTimeInMillis());
            forecast.setDate(date);


            forecast.setHumidity(jsonForecast.getInt("humidity"));
            forecast.setWindSpeed(jsonForecast.getDouble("speed"));

            JSONObject jsonTemp = jsonForecast.getJSONObject("temp");
            forecast.setHigh(jsonTemp.getDouble("max"));
            forecast.setLow(jsonTemp.getDouble("min"));
            forecast.setTemp(jsonTemp.getDouble("day"));

            JSONObject jsonWeather = jsonForecast.getJSONArray("weather").getJSONObject(0);
            forecast.setDescription(jsonWeather.getString("main"));
            forecast.setIconId(jsonWeather.getString("icon"));
            forecasts.add(forecast);
        }
        return forecasts;
    }


}
