package com.example.lab2.ui.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class testViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public testViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is test fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}