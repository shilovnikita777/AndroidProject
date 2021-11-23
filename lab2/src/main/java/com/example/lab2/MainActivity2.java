package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lab2.databinding.ActivityMain2Binding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity2 extends AppCompatActivity {


    private WearableRecyclerView mRecyclerView;
    private ActivityMain2Binding binding;

    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Tag","1");
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRecyclerView = binding.recyclerView;

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitInterface myRetInterface = retrofit.create(RetrofitInterface.class);
        Call<List<Example>> call = myRetInterface.getBooks();
        Log.i("Tag","2");
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if(response.isSuccessful()) {
                    List<Example> responseList = response.body();
                    responseList.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));

                    mAdapter = new MyRecyclerViewAdapter(responseList);
                    mRecyclerView.setAdapter(mAdapter);

                }
                else {
                    Log.i("MyTag", "Fail");
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable e) {
                e.printStackTrace();
            }
        });

        mRecyclerView.setLayoutManager(new WearableLinearLayoutManager(getBaseContext()));
        //mRecyclerView.setCircularScrollingGestureEnabled(true);
        //mRecyclerView.setEdgeItemsCenteringEnabled(true);
    }
}