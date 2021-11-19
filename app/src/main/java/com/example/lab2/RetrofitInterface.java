package com.example.lab2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("Lpirskaya/JsonLab/master/Books.json")
    Call<List<Example>> getBooks();
}
