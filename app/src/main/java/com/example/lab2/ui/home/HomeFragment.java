package com.example.lab2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lab2.Example;
import com.example.lab2.R;
import com.example.lab2.RetrofitInterface;
import com.example.lab2.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.lab2.MyRecyclerViewAdapter;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private SwipeRefreshLayout mySwipeRefreshLayout;

    List<Example> responseList;

    private boolean typeOfSort = true;

    String[] myStringBooks;
    String[] myStringAuthors;


    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });


        //Recycler view train
        mRecyclerView = root.findViewById(R.id.recycler_view_recipes);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mySwipeRefreshLayout  = root.findViewById(R.id.mySwipeRefreshLayout);


        //Train json
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitInterface myRetInterface = retrofit.create(RetrofitInterface.class);
        Call<List<Example>> call = myRetInterface.getBooks();

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if(response.isSuccessful()) {
                    responseList = response.body();
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
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (typeOfSort == false) {
                    responseList.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                } else {
                    responseList.sort((o1, o2) -> (-1) * o2.getName().compareTo(o1.getName()));
                }

                typeOfSort = !typeOfSort;

                mAdapter.notifyDataSetChanged();

                mySwipeRefreshLayout.setRefreshing(false);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onSortBooks()
    {
        boolean sorted = false;
        String temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < myStringBooks.length - 1; i++) {
                if (myStringBooks[i].compareTo(myStringBooks[i+1]) > 0) {
                    temp = myStringBooks[i];
                    myStringBooks[i] = myStringBooks[i+1];
                    myStringBooks[i+1] = temp;

                    temp = myStringAuthors[i];
                    myStringAuthors[i] = myStringAuthors[i+1];
                    myStringAuthors[i+1] = temp;

                    sorted = false;
                }
            }
        }
    }
}