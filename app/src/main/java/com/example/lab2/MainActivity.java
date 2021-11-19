package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    String[] emails;
    String[] passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emails = getResources().getStringArray(R.array.emails);
        passwords = getResources().getStringArray(R.array.passwords);
    }

    public void OnMyButtonClick(View view)
    {
        EditText emailBox = findViewById(R.id.editTextEmailAddress);
        EditText passwordBox = findViewById(R.id.editTextPassword);

        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();

        for (int i = 0; i < emails.length; ++i)
        {
            if (emails[i].equals(email) && passwords[i].equals(password))
            {
                Intent myIntent = new Intent(this,MainActivity2.class);
                startActivity(myIntent);
            }
        }
    }
}