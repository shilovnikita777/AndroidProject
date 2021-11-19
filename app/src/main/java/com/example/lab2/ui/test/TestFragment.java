package com.example.lab2.ui.test;

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

import com.example.lab2.Example;
import com.example.lab2.databinding.FragmentTestBinding;

public class TestFragment extends Fragment {

    private testViewModel testViewModel;
    private FragmentTestBinding binding;

    private static Example myBook;
    private TextView bookNameTextView;
    private TextView bookAuthorTextView;
    private TextView bookDateTextView;
    private TextView bookGenresTextView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        testViewModel = new ViewModelProvider(this).get(testViewModel.class);

        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //
        bookNameTextView = binding.bookName;
        bookAuthorTextView = binding.bookAuthor;
        bookDateTextView = binding.bookDate;
        bookGenresTextView = binding.bookGenres;

        if (getArguments()!=null) {

            myBook = (Example) getArguments().getSerializable("oneBook");
            Log.i("Tag", myBook.getName() + " " + myBook.getAuthor() + " " + " jaja");

            bookNameTextView.setText(myBook.getName());
            bookAuthorTextView.setText(myBook.getAuthor());
            bookDateTextView.setText(myBook.getPublicationDate()+"");
            bookGenresTextView.setText(myBook.getGenre());

        }
        else if (myBook != null)
        {
            bookNameTextView.setText(myBook.getName());
            bookAuthorTextView.setText(myBook.getAuthor());
            bookDateTextView.setText(myBook.getPublicationDate()+"");
            bookGenresTextView.setText(myBook.getGenre());
        }

        testViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}