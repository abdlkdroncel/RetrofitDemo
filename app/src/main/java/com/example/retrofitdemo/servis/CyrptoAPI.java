package com.example.retrofitdemo.servis;

import com.example.retrofitdemo.model.CyrptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CyrptoAPI {



    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Call<List<CyrptoModel>> getData();
}
