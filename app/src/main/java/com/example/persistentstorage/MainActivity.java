package com.example.persistentstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String usernameKey = "username";

    public void onClick(View view) {
        EditText myTextField = (EditText) findViewById(R.id.username);
        String str = myTextField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("persistentstorage", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",str).apply();
        goToNotes(str);
    }

    public void goToNotes(String s) {
        Intent intent = new Intent(this, NotePage.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("persistentstorage", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            Intent intent = new Intent(this, NotePage.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
        }
    }
}