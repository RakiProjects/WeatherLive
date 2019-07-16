package com.hfad.weatherlive;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ForecastActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listViewForecast;
    private ForecastArrayAdapter forecastArrayAdapter;


    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private ForecastViewModel forecastViewModel;
    private RecyclerView rcView;
    private MainRecyclerViewAdapter rcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recycler_view);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///////////////
        rcView = (RecyclerView) findViewById(R.id.recycler_view);

        // generateForecastList();

        generateForecastRecyclerView();

        forecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel.class);
        forecastViewModel.forecastListLiveData.observe(this, new Observer<ForecastResponse>() {
            // na setValue se poziva onChange
            @Override
            public void onChanged(ForecastResponse forecastResponse) {
                if (forecastResponse == null) return;

                if (forecastResponse.getThrowable() != null) {
                    //...
                }
                // da dodamo value listi, jer je inicijalizovano da je null, i, koja sa notifyDataSetChanged promeni u adapteru
                rcAdapter.updateForecastList(forecastResponse.getForecasts());  // getForecasts je metod iz ForecastResponse, isto kao sto se zvao metod iz ForecastList
            }
        });
        // ovde ce se pozvati setValue() na MutableLiveData forecastListLiveData
        forecastViewModel.getForecastList();
    }

    private void generateForecastList() {
        listViewForecast = (ListView) findViewById(R.id.listView);
        forecastArrayAdapter = new ForecastArrayAdapter(this, new ArrayList<Forecast>());
        listViewForecast.setAdapter(forecastArrayAdapter);
    }

    private void generateForecastRecyclerView() {
        rcAdapter = new MainRecyclerViewAdapter(ForecastActivity.this, new ArrayList<Forecast>());
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(rcAdapter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Clicked item ", Toast.LENGTH_SHORT).show();
               //MapsActivity.start(this);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
        }
    }
}
