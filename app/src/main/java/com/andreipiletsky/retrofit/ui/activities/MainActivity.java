package com.andreipiletsky.retrofit.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.andreipiletsky.retrofit.R;
import com.andreipiletsky.retrofit.pojo.Contacts;
import com.andreipiletsky.retrofit.pojo.Example;
import com.andreipiletsky.retrofit.pojo.Main;
import com.andreipiletsky.retrofit.pojo.Weather;
import com.andreipiletsky.retrofit.ui.adapters.RecyclerAdapter;
import com.andreipiletsky.retrofit.web.ApiClient;
import com.andreipiletsky.retrofit.web.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Contacts> contactsList;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Contacts>> call = apiInterface.getContacts();
        call.enqueue(new Callback<List<Contacts>>() {

            @Override
            public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                contactsList = response.body();
                adapter = new RecyclerAdapter(contactsList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Contacts>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

//        Call<Example> call2 = apiInterface.getExample();
//        call2.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                List<Weather> weatherData = response.body().getWeather();
//                Main mainData= response.body().getMain();
//                Log.d("Artur",response.body().toString());
//                Log.d("Artur",weatherData.toString());
//                Log.d("Artur",mainData.getTemp().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//
//            }
//        });
    }
}