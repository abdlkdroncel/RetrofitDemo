package com.example.retrofitdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.adapter.ModelAdapter;
import com.example.retrofitdemo.model.CyrptoModel;
import com.example.retrofitdemo.servis.CyrptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CyrptoModel> model;
    private String base="https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    ModelAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recView);

        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder()
                .baseUrl(base)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();

    }

    private void loadData(){
        CyrptoAPI cyrptoAPI=retrofit.create(CyrptoAPI.class);
        Call<List<CyrptoModel>> call=cyrptoAPI.getData();
        call.enqueue(new Callback<List<CyrptoModel>>() {
            @Override
            public void onResponse(Call<List<CyrptoModel>> call, Response<List<CyrptoModel>> response) {
                if(response.isSuccessful()){
                    List<CyrptoModel> resList=response.body();
                    model=new ArrayList<>(resList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter=new ModelAdapter(model);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CyrptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}