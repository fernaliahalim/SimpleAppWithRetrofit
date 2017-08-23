package com.example.fernalia.simpleappwithretrofit.activity;

/**
 * Created by Fernalia on 23/08/2017.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fernalia.simpleappwithretrofit.R;
import com.example.fernalia.simpleappwithretrofit.model.Forcast;
import com.example.fernalia.simpleappwithretrofit.model.List;
import com.example.fernalia.simpleappwithretrofit.model.Weather;
import com.example.fernalia.simpleappwithretrofit.rest.ApiClient;
import com.example.fernalia.simpleappwithretrofit.rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ListView lv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_list = (ListView) findViewById(R.id.lv_list);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<Forcast> call = apiService.getWeatherForcast("94043 CUSA", "json", "metric", 14);
        call.enqueue(new Callback<Forcast>() {
            @Override
            public void onResponse(retrofit2.Call<Forcast> call, Response<Forcast> response) {
                java.util.List<List> lists = response.body().getList();
                Log.d(TAG, "Number of List received: " + lists.size());

                if(lists.size() > 0){
                    java.util.List<String> description =  new ArrayList<>();

                    for(int i = 0; i < lists.size(); i++){
                        List list = lists.get(i);

                        Weather weather = list.getWeather().get(0);
                        description.add(weather.getDescription());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, description);
                    lv_list.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Forcast> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
