package com.example.lab2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    String[] emails;
    String[] passwords;

    EditText emailBox;
    EditText passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emails = getResources().getStringArray(R.array.emails);
        passwords = getResources().getStringArray(R.array.passwords);
    }

    public void OnMyButtonClick(View view)
    {
        emailBox = findViewById(R.id.editTextEmailAddress);
        passwordBox = findViewById(R.id.editTextPassword);

        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();

        for (int i = 0; i < emails.length; ++i)
        {
            if (emails[i].equals(email) && passwords[i].equals(password))
            {
                Intent myIntent = new Intent(this,MainActivity2.class);
                startActivity(myIntent);
                break;
            }
            else
            {
                emailBox.setTextColor(Color.parseColor("#FF4500"));
                emailBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF4500")));

                passwordBox.setTextColor(Color.parseColor("#FF4500"));
                passwordBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF4500")));
            }
        }
        emailBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String changedEmailString = s.toString();
                //if (changedEmailString.length() != s.toString().length()) {
                emailBox.setTextColor(Color.parseColor("#393E46"));
                emailBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#393E46")));

                passwordBox.setTextColor(Color.parseColor("#393E46"));
                passwordBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#393E46")));
                //}
            }
        });
        passwordBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String changedPasswordString = s.toString();
                //if (changedPasswordString.length() != s.toString().length()) {
                emailBox.setTextColor(Color.parseColor("#393E46"));
                emailBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#393E46")));

                passwordBox.setTextColor(Color.parseColor("#393E46"));
                passwordBox.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#393E46")));
                //}
            }
        });
    }
}