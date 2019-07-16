package com.hfad.weatherlive;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Forecast> forecasts = new ArrayList<>();
    private Context context;

    public MainRecyclerViewAdapter(Context context, ArrayList<Forecast> forecasts) {
        this.context = context;
        this.forecasts = forecasts;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView high;
        TextView desc;
        TextView low;
        ImageView iconId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            desc = (TextView) itemView.findViewById(R.id.desc);
            high = (TextView) itemView.findViewById(R.id.high);
            low = (TextView) itemView.findViewById(R.id.low);
            iconId = (ImageView) itemView.findViewById(R.id.icon);
        }
    }

    @NonNull
    @Override
    // called right when the adapter is created and is used to initialize your ViewHolder(s). creates a new view holder when there are no existing view holders which the RecyclerView can reuse.
    public MainRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);    // takes as input XML file and builds the View objects from it
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        ((MyViewHolder) viewHolder).date.setText(forecasts.get(position).getDate());
        ((MyViewHolder) viewHolder).desc.setText(forecasts.get(position).getWeather().get(0).getDescription());
        ((MyViewHolder) viewHolder).high.setText(String.valueOf(forecasts.get(position).getTemp().getHigh()));
        ((MyViewHolder) viewHolder).low.setText(String.valueOf(forecasts.get(position).getTemp().getLow()));
        ((MyViewHolder) viewHolder).iconId.setImageResource(getImageResource(forecasts.get(position).getWeather().get(0).getIconId()));

        Log.d("Temp" + position, String.valueOf(forecasts.get(position).getTemp().getHigh()));
    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(forecasts.size()));
        return forecasts.size();
    }

    public void updateForecastList(List<Forecast> list) {
        forecasts.clear();
        forecasts.addAll(list);

        // pozove adapter da je doslo do promene
        notifyDataSetChanged();
    }

    private int getImageResource(String iconId) {
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
        return R.drawable.fi01d;
    }
}
